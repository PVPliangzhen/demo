package com.ablesky.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ablesky.ui.activity.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
}
