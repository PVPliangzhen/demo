package com.ablesky.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.ablesky.app.AppContext;
import com.ablesky.ui.activity.R;
import com.ablesky.utils.DialogUtils;
import com.ablesky.utils.SpUtils;
import com.umeng.ThirdPartyUtil;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by liangzhen on 2018/1/23.
 */

public class SplashActivity extends BaseActivity{

    private AppContext appContext;
    private DialogUtils dialogUtils;
    private final int REQUEST_PERMINSSION_WRITE_STORAGE_CODE = 0x01;
    private String password;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = AppContext.application;
        setBaseContentView(R.layout.activity_splash, false,false);
        dialogUtils = new DialogUtils(this, R.style.dialog_user);
        checkStoragePower();
    }

    private void checkStoragePower() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(appContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            showGuideDialog(1);
        }else{
            initData();
        }
    }

    private void initData() {
        password = SpUtils.getInstance(appContext).get("password", "");
        username = SpUtils.getInstance(appContext).get("username", "");
        initUI();
    }

    private void initUI() {
        final String platformName = SpUtils.getInstance(appContext).get(ThirdPartyUtil.THIRDLOGIN, "");
    }

    private void showGuideDialog(int type) {
        switch (type){
            case 1:
                dialogUtils.setDialog_ok("下一步");
                dialogUtils.hideCancel();
                dialogUtils.setDialog_title("开启能力天空");
                dialogUtils.setDialog_text("为了您正常使用能力天空,需要以下权限\n\n存储空间");
                dialogUtils.setCanceledOnTouchOutside(false);
                setDialogListener(1);
                dialogUtils.show();
                break;
            case 2:
                dialogUtils.setDialog_ok("确定");
                dialogUtils.setDialog_cancel("取消");
                dialogUtils.setDialog_title("请允许存储空间权限");
                dialogUtils.setDialog_text("我们需要获取存储空间权限,为您提供视频等知识资料的下载服务，否则您将无法正常使用此APP学习");
                dialogUtils.showCancel();
                dialogUtils.setCanceledOnTouchOutside(false);
                setDialogListener(2);
                dialogUtils.show();
                break;
            case 3:
                dialogUtils.setDialog_ok("去设置");
                dialogUtils.setDialog_cancel("拒绝");
                dialogUtils.setDialog_title("请允许存储空间权限");
                dialogUtils.setDialog_text("我们需要获取存储空间权限,为您提供视频等知识资料的下载服务，否则您将无法正常使用此APP学习");
                dialogUtils.showCancel();
                dialogUtils.setCanceledOnTouchOutside(false);
                setDialogListener(3);
                dialogUtils.show();
                break;
        }
    }

    private void setDialogListener(final int type) {
        dialogUtils.setOnOkListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                switch (type){
                    case 1:
                    case 2:
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMINSSION_WRITE_STORAGE_CODE);
                        dialogUtils.dismiss();
                        break;
                    case 3:
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", "com.ablesky.ui.activity", null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialogUtils.dismiss();
                        finish();
                        break;
                }
            }
        });
        dialogUtils.setOnCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case 2:
                        finish();
                        break;
                    case 3:
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMINSSION_WRITE_STORAGE_CODE){
            boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initData();
            }else{
                if (isTip){
                    if (dialogUtils.isShowing()){
                        dialogUtils.dismiss();
                    }
                    showGuideDialog(2);
                }else{
                    if (dialogUtils.isShowing()){
                        dialogUtils.dismiss();
                    }
                    showGuideDialog(3);

                }
            }
        }
    }
}
