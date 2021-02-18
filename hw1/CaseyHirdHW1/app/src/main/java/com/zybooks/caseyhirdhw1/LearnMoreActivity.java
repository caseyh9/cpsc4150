/*
    Casey Hird
    C12932552
    crhird@clemson.edu
    ******************
    LearnMoreActivity.java hosts the activity when the "Learn More" button is clicked
 */

package com.zybooks.caseyhirdhw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LearnMoreActivity extends AppCompatActivity {

    /*
        onCreate() initializes the LearnMore activity
        Pre: none
        Post: the learn more screen will be displayed, and the user can scroll through to
        see the additional images and biographical information
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);
    }
}