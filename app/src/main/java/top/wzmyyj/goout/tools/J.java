package top.wzmyyj.goout.tools;

import android.text.TextUtils;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by wzm on 2018/4/25 0025.
 */

public class J {
    public static String getName(UserInfo info) {

        if (info == null)
            return null;
        if (!TextUtils.isEmpty(info.getNotename())) {
            return info.getNotename();
        } else if (!TextUtils.isEmpty(info.getNickname())) {
            return info.getNickname();
        } else {
            return info.getUserName();
        }
    }

    public static String getSignature(UserInfo info){
        if (info == null)
            return null;
        if(!TextUtils.isEmpty(info.getSignature())){
            return info.getSignature();
        }else{
            return "No Signature";
        }
    }
}
