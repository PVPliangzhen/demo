package com.ablesky.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ablesky.ui.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangzhen on 2018/1/30.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private TextView guide_login;
    private TextView guide_skip;

    private static final int[] pics = {R.drawable.guide1, R.drawable.guide2,
            R.drawable.guide3};

    private ImageView[] dots;

    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.guide_layout, false, true);
        List<View> views = new ArrayList<>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int pic : pics){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(pic);
            views.add(imageView);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        guide_login = (TextView) findViewById(R.id.guide_login);
        guide_skip = (TextView) findViewById(R.id.guide_skip);
    }

    @Override
    public void onClick(View v) {

    }
}
