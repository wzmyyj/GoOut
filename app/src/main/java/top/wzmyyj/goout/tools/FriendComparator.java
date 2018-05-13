package top.wzmyyj.goout.tools;

import java.util.Comparator;

import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.wzm_sdk.java.FirstChar;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class FriendComparator implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        UserInfo a = (UserInfo) lhs;
        UserInfo b = (UserInfo) rhs;

        if (FirstChar.first(J.getName(a)).charAt(0) > FirstChar.first(J.getName(b)).charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }
}