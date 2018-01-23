package com.umeng;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;

import com.umeng_social_sdk_res_lib.R;

/**
 * Created by qli on 2017/8/24.
 */

public class DialogUtils {

    public Dialog showDialog(Context context) {
        Dialog alertDialog = new Dialog(context);
        alertDialog.setContentView(R.layout.loading_dialog_share);
        try{
            int dividerID=context.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider=alertDialog.findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.show();
        return alertDialog;
    }

}
