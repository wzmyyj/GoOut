package top.wzmyyj.goout.tools;

import android.text.TextUtils;

import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

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

    public static String getName(GroupInfo info) {

        if (info == null)
            return null;
        if (!TextUtils.isEmpty(info.getGroupName())) {
            return info.getGroupName();
        } else {
            return "群组：" + info.getGroupID();
        }
    }

    public static String getD(GroupInfo info) {

        if (info == null)
            return null;
        if (TextUtils.isEmpty(info.getGroupDescription())) {
            return "无";
        } else {
            return info.getGroupDescription().replace("!@#$%^&*()_+", "");
        }
    }

    public static String getSignature(UserInfo info) {
        if (info == null)
            return null;
        if (!TextUtils.isEmpty(info.getSignature())) {
            return info.getSignature();
        } else {
            return "No Signature";
        }
    }


    public static String getGender(UserInfo info) {
        if (info == null)
            return null;
        if (info.getGender() == UserInfo.Gender.female) {
            return "女";
        } else if (info.getGender() == UserInfo.Gender.male) {
            return "男";
        } else {
            return "保密";
        }
    }


    public static String getBirthday(UserInfo info) {
        if (info == null)
            return null;
        String t = TimeUtil.changeToString(info.getBirthday(), "yyyy-MM-dd");
        return t;
    }


}
