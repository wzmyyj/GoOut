package top.wzmyyj.goout.tools;

import android.app.Activity;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * Created by wzm on 2018/4/24 0024.
 */

public class ThemeManager {

    public static boolean setStatusBarMode(Activity activity, boolean idDark) {
        if (idDark) {
            return QMUIStatusBarHelper.setStatusBarLightMode(activity);
        } else {
            return QMUIStatusBarHelper.setStatusBarDarkMode(activity);
        }
    }
}
