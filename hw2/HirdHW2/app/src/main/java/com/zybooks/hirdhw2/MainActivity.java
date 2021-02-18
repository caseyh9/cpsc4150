/*
    MainActivity.java
    Hosts the main activity of the project.
    Note the code to start a new activity is from zybooks 3.5

    Casey Hird
    C12932552
    crhird@clemson.edu
 */
package com.zybooks.hirdhw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    onStartGame() starts the GameActivity when the "START GAME" button is clicked
    Pre: view is the "START GAME" button
    Post: the MainActivity will be stopped and the GameActivity will be displayed
    */
    public void onStartGameClick(View view) {
        // This code from zybooks 3.5
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    /*
    onAboutClick() starts the AboutActivity when the "ABOUT PIC DICE" button is clicked
    Pre: view is the "ABOUT PIG DICE" button
    Post: the MainActivity will be stopped and the AboutActivity will be displayed
    */
    public void onAboutClick(View view) {
        // This code from zybooks 3.5
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}