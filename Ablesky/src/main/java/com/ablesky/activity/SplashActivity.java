package com.ablesky.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.ablesky.ui.activity.R;

/**
 * Created by liangzhen on 2018/1/23.
 */

public class SplashActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_splash, false,true);
        getSupportActionBar().hide();
    }
}
