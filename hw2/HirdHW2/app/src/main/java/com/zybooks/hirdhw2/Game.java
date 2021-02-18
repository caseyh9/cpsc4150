/*
    Game.java
    Models a game of Pig Dice.

    Casey Hird
    C12932552
    crhird@clemson.edu
 */

package com.zybooks.hirdhw2;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Game {

    private int winning_score;
    Random rand;
    private int player_total;
    private int bank_total;
    private int round;
    private boolean player_turn;

    /*
        Creates a new game.
        Pre: target score is the value entered by the user.
        Post: a new game is set up where both players start with 0, the round score is 0,
        and the target score is "target_score".
     */
    public Game(int target_score) {
        player_total = bank_total = 0;
        round = 1;
        player_turn = true;
        rand = new Random();
        winning_score = target_score;
    }

    /*
        Resets the game values.
        Pre: game has finished.
        Post: the game values are reset to both players with 0, the round score is 0.
     */
    public void resetGame() {
        player_total = bank_total = 0;
        round = 1;
        player_turn = true;
    }

    /*
        Checks if the game has been won.
        Pre: game has been created.
        Post: returns true IFF the game has been won by either player.
     */
    public boolean isGameWon() {
        if (player_total >= winning_score || bank_total >= winning_score) return true;
        return false;
    }

    public int getPlayer_total() {return player_total;}
    public int getBank_total() {return bank_total;}
    public boolean isPlayer_turn() {return player_turn;}
    public void changeTurn() {player_turn = !player_turn;}
    public int getWinning_score() {return winning_score;}
    public void incrementRound() {round++;}
    public int getRound() {return round;}
    public int addPlayerScore(int val) {
        player_total += val;
        return player_total;
    }
    public int addBankScore(int val) {
        bank_total += val;
        return bank_total;
    }
}
