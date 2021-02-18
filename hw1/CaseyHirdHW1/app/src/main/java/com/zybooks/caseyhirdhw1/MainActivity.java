/*
    Casey Hird
    C12932552
    crhird@clemson.edu
    ******************
    MainActivity.java hosts the first activity used when the app is opened.
    References: zybooks 2.9 and 3.5
 */

package com.zybooks.caseyhirdhw1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private int homeScreenImageFlag = 0;
    private ImageView homeImage;

    /*
        onCreate() initializes the main activity
        Pre: none
        Post: the home screen image will be displayed, and cycle through the 3 given images
        when clicked, the greeting message and button will also be displayed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeImage = findViewById(R.id.homeScreenImage);

        // Code from zybooks 2.9
        homeImage.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick (View v){
            homeScreenImageFlag++;
            if (homeScreenImageFlag == 3) homeScreenImageFlag = 0;

            int imageResource;
            switch (homeScreenImageFlag) {
                case 0:
                    imageResource = R.drawable.headshot;
                    break;
                case 1:
                    imageResource = R.drawable.roomies;
                    break;
                default:
                    imageResource = R.drawable.acc_champ;
            }

            homeImage.setImageDrawable(getResources().getDrawable(imageResource));
        }
        });
    }

    /*
        onLearnMoreClick() starts the LearnMoreActivity when the "Learn More" button is clicked
        Pre: view is the "Learn More" button
        Post: the MainActivity will be stopped and the LearnMoreActivity will be displayed
     */
    public void onLearnMoreClick(View view) {
        // This code from zybooks 3.5
        Intent intent = new Intent(this, LearnMoreActivity.class);
        startActivity(intent);
    }
}