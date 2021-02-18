/*
    AboutActivity.java
    Hosts the about activity of the project, which gives the user a description of the
    pig dice game.

    Casey Hird
    C12932552
    crhird@clemson.edu
 */

package com.zybooks.hirdhw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}