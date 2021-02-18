/*
    GameActivity.java
    Hosts the game activity of the project in which the user plays a game of pig dice
    against the computerized banker.
    Note the alert dialog code is from stack overflow and dice code is from zybooks 4.2

    Casey Hird
    C12932552
    crhird@clemson.edu
 */

package com.zybooks.hirdhw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private Dice die;
    private int round_score;
    private int target_score;
    private ImageView dieView;
    private CountDownTimer mTimer;
    TextView player_tot_view;
    TextView bank_tot_view;
    TextView round_score_view;
    TextView round_label;
    TextView target_score_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Connect to views in layout
        dieView = (ImageView) findViewById(R.id.die_image);
        player_tot_view = (TextView) findViewById(R.id.player_total_text);
        bank_tot_view = (TextView) findViewById(R.id.bank_total_text);
        round_score_view = (TextView) findViewById(R.id.round_score_text);
        round_label = (TextView) findViewById(R.id.round_label);
        target_score_view = (TextView) findViewById(R.id.target_score_text);

        // Get target score and start game pieces
        die = new Dice(6);
        round_score = 0;
        getTargetScore();
    }

    /*
        Creates an Alert Dialog to get the target score from the user.
        Pre: the activity has been new game is starting.
        Post: a new game is set up where both players start with 0, the round score is 0,
        and the target score has been set by the user.
     */
    private void getTargetScore() {
        // stack overflow
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setTitle(R.string.target_score);
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                target_score = Integer.parseInt(input.getText().toString());
                target_score_view.setText(String.valueOf(target_score));
                game = new Game(target_score);
            }
        });
       // builder.show();
        // Set color of alert dialog buttons (from stack overflow)
        AlertDialog alert = builder.create();
        alert.show();

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundColor(getResources().getColor(R.color.yale_blue));
    }

    /*
        Shows the Dice image on the screen.
        Pre: the dice image and number are in-line.
        Post: the drawable corresponding to the current dice number is displayed.
     */
    // From zybooks 4.2
    private void showDice() {
        Drawable diceDrawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            diceDrawable = getResources().getDrawable(die.getImageId(),
                    getApplicationContext().getTheme());
        } else {
            diceDrawable = getResources().getDrawable(die.getImageId());
        }

        dieView.setImageDrawable(diceDrawable);
        dieView.setContentDescription(Integer.toString(die.getNumber()));
    }

    /*
        Rolls the die.
        Pre: use has clicked the roll button, or the computer is rolling.
        Post: the dice has been rolled and the new number added to the round score
        (or the round ended if a 1 is rolled).
     */
    // From zybooks 4.2
    private void rollDie() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {
                die.roll();
                showDice();
            }
            public void onFinish() {
                afterRoll();
            }
        }.start();
    }

    /*
        Banks the round score for the user.
        Pre: used clicked the bank score button.
        Post: the users score is increased by the round value, if the game is won the user
        is notified, if not the next round begins.
     */
    public void onBankClick(View view) {
        bankUserScore();
    }

    /*
        Banks the round score.
        Pre: the player of banker has chosen to bank the round score.
        Post: the current user increases their score by the round value, and if the game is
        the appropriate dialog is entered, otherwise the game is set for a new round
        (round score set to 0, turn changes).
     */
    private void bankUserScore() {
        if (game.isPlayer_turn()) {
            game.addPlayerScore(round_score);
            player_tot_view.setText(String.valueOf(game.getPlayer_total()));
        }
        else {
            game.addBankScore(round_score);
            bank_tot_view.setText(String.valueOf(game.getBank_total()));
        }
        if (game.isGameWon()) {
            gameWonDialog();
        } else {
            game.changeTurn();
            round_score = 0;
            round_score_view.setText(String.valueOf(round_score));
            String label;
            if (game.isPlayer_turn()) {
                game.incrementRound();
                label = "Round " + game.getRound() + " - Player's Turn";
            }
            else {
                label = "Round " + game.getRound() + " - Banker's Turn";
            }
            round_label.setText(label);
            if (!game.isPlayer_turn())
                doBankTurn();
        }
    }

    /*
            Resets the game.
            Pre: the player has chosen to play again.
            Post: a new game is set up where both players start with 0, the round score is 0,
            and the target score is taken from the user.
         */
    private void resetGame() {
        game.resetGame();
        round_score = 0;

        // Reset values in views
        player_tot_view.setText(String.valueOf(game.getPlayer_total()));
        bank_tot_view.setText(String.valueOf(game.getBank_total()));
        round_score_view.setText(String.valueOf(round_score));
        String begin_label = "Round " + game.getRound() + " - Player's Turn";
        round_label.setText(begin_label);

        getTargetScore();
    }

    /*
        Informs the user the game has been won and gets their choice.
        Pre: the current game has been won.
        Post: the player has been informed of the game ending, and the app has either begun a
        new game or returned to the home screen.
     */
    private void gameWonDialog() {
        // stack overflow
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((game.isPlayer_turn()) ? R.string.player_win_title : R.string.bank_win_title);
        builder.setMessage((game.isPlayer_turn()) ? R.string.player_win_message : R.string.bank_win_message);

        // Set up the buttons
        builder.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
            }
        });
        builder.setNegativeButton(R.string.return_to_home, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                returnToMain();
            }
        });
        //builder.show();

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setBackgroundColor(getResources().getColor(R.color.bank_red));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundColor(getResources().getColor(R.color.yale_blue));
    }

    /*
        Returns to main activity.
        Pre: the user has chosen to return to home after ended game.
        Post: the app is back at the main activity.
     */
    private void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
            Rolls the die.
            Pre: the player has clicked to roll again.
            Post: the die has been rolled, and the score has been added to the round total
            (or the turn has changed if a 1 is rolled).
         */
    public void onRollAgainClick(View view) {
        rollDie();
    }

    /*
        Responds to the roll of the die.
        Pre: the die has just been rolled.
        Post: if a 1 was rolled, the round score is 0 and the next turn begins, otherwise
        round score is increased by the current die values and the current turn continues.
     */
    private void afterRoll() {
        if (die.getNumber() == 1) {
            round_score = 0;
            round_score_view.setText(String.valueOf(round_score));
            game.changeTurn();
            String label;
            if (game.isPlayer_turn()) {
                game.incrementRound();
                label = "Round " + game.getRound() + " - Player's Turn";
            }
            else {
                label = "Round " + game.getRound() + " - Banker's Turn";
            }
            round_label.setText(label);
        }
        else {
            round_score += die.getNumber();
            round_score_view.setText(String.valueOf(round_score));
        }
        if (!game.isPlayer_turn())
            doBankTurn();
    }

    /*
        The Banker executes a turn.
        Pre: it is the bankers turn.
        Post: the banker has either chosen to roll the die or bank the current score.
     */
    private void doBankTurn() {
        // Wait 1 second so player can understand what is happening
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        if (round_score + game.getBank_total() >= game.getWinning_score() || round_score >= 20) {
            bankUserScore();
        }
        else
            rollDie();
    }
}