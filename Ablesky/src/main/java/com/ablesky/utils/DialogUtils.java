package com.ablesky.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ablesky.ui.activity.R;

/**
 * Created by liangzhen on 2018/1/29.
 */

public class DialogUtils extends Dialog{

    //dialog提示标题
    private TextView dialog_title;
    // dialog提示文字
    private TextView dialog_text;
    // dialog取消按钮
    private TextView dialog_cancel;
    // dialog确认按钮
    private TextView dialog_ok;
    //取消和确定中间的分割线
    private View dialog_view_line;

    public DialogUtils(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.custom_dialog, null);
        ScrollView scrollView = (ScrollView) contentView.findViewById(R.id.scrollview);
        scrollView.setVerticalScrollBarEnabled(false);
        dialog_text = (TextView) contentView.findViewById(R.id.dialog_text);
        dialog_cancel = (TextView) contentView.findViewById(R.id.dialog_left);
        dialog_ok = (TextView) contentView.findViewById(R.id.dialog_right);
        dialog_view_line = contentView.findViewById(R.id.dialog_view_line);
        dialog_title = (TextView) contentView.findViewById(R.id.dialog_title);
        setContentView(contentView);
    }

    public void setDialog_text(String text) {
        dialog_text.setText(text);
    }

    public void setDialog_cancel(String text) {
        dialog_cancel.setText(text);
    }

    public void setDialog_ok(String text) {
        dialog_ok.setText(text);
    }

    public void setDialog_title(String text) {
        dialog_title.setText(text);
    }

    public void hideCancel(){
        dialog_cancel.setVisibility(View.GONE);
        dialog_view_line.setVisibility(View.GONE);
        if (dialog_ok != null) {
            dialog_ok.setBackgroundResource(R.drawable.custom_dialog_leftandright);
        }
    }

    public void showCancel() {
        dialog_cancel.setVisibility(View.VISIBLE);
        dialog_view_line.setVisibility(View.VISIBLE);
    }

    public void setOnCancelListener(
            android.view.View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            dialog_cancel.setOnClickListener(onClickListener);
        }
    }

    public void setOnOkListener(
            android.view.View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            dialog_ok.setOnClickListener(onClickListener);
        }
    }
}
