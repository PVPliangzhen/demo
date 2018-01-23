package com.umeng.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.umeng.DialogUtils;
import com.umeng.ThirdPartyUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class ShareUtils {


    public interface OnShareResultListener {
        void onShareSuccess(SHARE_MEDIA platform);
    }

    /**
     * @param activity 上下文
     * @param content  标题
     * @param url      URL链接
     * @param imgURL   图片链接
     */
    public static void openShareBoard(final Activity activity, String content, String title,
                                      final String url, final String imgURL) {
        if (TextUtils.isEmpty(content)) {
            content = " ";
        }
        final UMShareListener umShareListener = getUmShareListener(activity, false, null);
        UMWeb web = getUmWeb(activity, content, title, url, imgURL);
        new ShareAction(activity).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.MORE)
                .withMedia(web)
                .withText(title + "\n" + url)
                .setCallback(umShareListener)
                .open();
    }

    /**
     * @param activity 上下文
     * @param content  标题
     * @param url      URL链接
     * @param imgURL   图片链接
     */
    public static void openCustomShareBoard(Activity activity, Class customShareActivity, String content, String title,
                                            String url, String imgURL, String activityImgUrl, int activityId) {
        Intent intent = new Intent(activity, customShareActivity);
        intent.putExtra("content", content);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("imgURL", imgURL);
        intent.putExtra("activityImgUrl", activityImgUrl);
        intent.putExtra("activityId", activityId);
        activity.startActivity(intent);
    }

    /**
     * @param activity 上下文
     * @param content  标题
     * @param url      URL链接
     * @param imgURL   图片链接
     */
    public static void openShare(Activity activity, SHARE_MEDIA platform, String content, String title,
                                 String url, String imgURL, OnShareResultListener mOnShareResultListener) {
        if (TextUtils.isEmpty(content)) {
            content = " ";
        }
        UMShareListener umShareListener = getUmShareListener(activity, true, mOnShareResultListener);
        UMWeb web = getUmWeb(activity, content, title, url, imgURL);
        new ShareAction(activity).setPlatform(platform)
                .withMedia(web)
                .withText(title + "\n" + url)
                .setCallback(umShareListener)
                .share();
    }

    @NonNull
    private static UMWeb getUmWeb(Activity activity, String content, String title, String url, String imgURL) {
        if (!TextUtils.isEmpty(imgURL)) {
            UMImage umImage = new UMImage(activity, imgURL);
            UMWeb web = new UMWeb(url);
            web.setTitle(title);
            web.setDescription(content);
            web.setThumb(umImage);
            return web;
        }
        return null;
    }

    @NonNull
    private static UMShareListener getUmShareListener(final Activity activity, final boolean closePageOnSuccess, final OnShareResultListener mOnShareResultListener) {
        return new UMShareListener() {
            Dialog alertDialog;

            @Override
            public void onResult(SHARE_MEDIA platform) {
                if (alertDialog != null) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    alertDialog = null;
                }
                Log.d("UM_onResult", platform.toString());
                if (platform.name().equals("WEIXIN_FAVORITE")) {
                    Toast.makeText(activity, "收藏成功", Toast.LENGTH_SHORT).show();
                } else if (!platform.name().equals("MORE")) {
                    Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();
                }
                if (mOnShareResultListener != null) {
                    mOnShareResultListener.onShareSuccess(platform);
                }
                if (closePageOnSuccess) {
                    activity.finish();
                }
            }

            @Override
            public void onStart(SHARE_MEDIA share_media) {
                if (alertDialog != null) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    alertDialog = null;
                }
                alertDialog = new DialogUtils().showDialog(activity);
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable throwable) {
                if (alertDialog != null) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    alertDialog = null;
                }
                Log.d("UM_onError", platform.toString());
                String tips = "分享失败，" + throwable.getMessage();
                if (!TextUtils.isEmpty(platform.toString())) {
                    switch (platform.toString()) {
                        case "WEIXIN":
                        case "WEIXIN_CIRCLE":
                        case "WEIXIN_FAVORITE":
                            if (!ThirdPartyUtil.isWeixinAvilible(activity)) {
                                tips = "分享失败，没有安装微信客户端";
                            }
                            break;
                        case "QQ":
                            if (!ThirdPartyUtil.isWeixinAvilible(activity)) {
                                tips = "分享失败，没有安装QQ客户端";
                            }
                            break;
                    }
                }
                Toast.makeText(activity, tips, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                try {
                    if (alertDialog != null) {
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                        alertDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(activity, "取消分享", Toast.LENGTH_SHORT).show();
            }
        };
    }
}