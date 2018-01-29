package com.ablesky.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ablesky.swipebacklayout.SwipeBackActivity;
import com.ablesky.ui.activity.R;
import com.ablesky.utils.ExitAppUtils;
import com.ablesky.utils.UIUtils;

/**
 * Created by liangzhen on 2018/1/23.
 */

public class BaseActivity extends SwipeBackActivity{

    private boolean isMIUI;
    private boolean isFLYME;
    private RelativeLayout baseContentView;
    private int statusBarHeight;
    private View lollipop_statusBar_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitAppUtils.getInstance().addActivity(this);
        isMIUI = UIUtils.isOverMIUIV6();
        isFLYME = UIUtils.isOverFlymeV5();
        statusBarHeight = UIUtils.getStatusBarHeight(this);
    }

    public void setBaseContentView(int resId){
        setBaseContentView(resId, true);
    }

    public void setBaseContentView(int resId, boolean canSwipeBack){
        setBaseContentView(resId, canSwipeBack, 0, true);
    }

    public void setBaseContentView(int resId, boolean canSwipeBack, boolean isLightStatusBar){
        setBaseContentView(resId, canSwipeBack, R.color.transparent, isLightStatusBar);
    }

    public void setBaseContentView(int resId, boolean canSwipeBack, int color, boolean isLightStatusBar){
        getSwipeBackLayout().setEnableGesture(canSwipeBack);
        baseContentView = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.activity_base, null);
        View contentView = LayoutInflater.from(this).inflate(resId, baseContentView, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (color != R.color.transparent){
                    if (color == 0){
                        baseContentView.setBackgroundResource(R.color.color_ffffff);
                    }else{
                        baseContentView.setBackgroundResource(color);
                    }
                }
                if (isLightStatusBar){
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }else{
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
            }else{
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                if (color != R.color.transparent) {
                    if (color == 0) {
                        baseContentView.setBackgroundResource(R.color.color_e2e2e2);
                    } else {
                        baseContentView.setBackgroundResource(color);
                    }
                }
                lollipop_statusBar_mask = new View(this);
                baseContentView.addView(lollipop_statusBar_mask);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lollipop_statusBar_mask.getLayoutParams();
                if (layoutParams != null){
                    layoutParams.height = statusBarHeight;
                    layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                }
                if (isLightStatusBar){
                    lollipop_statusBar_mask.setBackgroundResource(R.color.colorAccent);
                }
            }
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
            if (color != R.color.transparent){
                baseContentView.setPadding(0, statusBarHeight, 0, 0);
            }
            baseContentView.addView(contentView);
            setContentView(baseContentView);
        }else{
            baseContentView.addView(contentView);
            setContentView(baseContentView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().delActivity(this);
    }

    @Override
    public void onSwipeFinish() {}
}
