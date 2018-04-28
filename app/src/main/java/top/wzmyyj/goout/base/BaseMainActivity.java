package top.wzmyyj.goout.base;

import android.os.Bundle;

import top.wzmyyj.goout.tools.ThemeManager;
import top.wzmyyj.wzm_sdk.activity.FVT_Activity;

/**
 * Created by wzm on 2018/4/24 0024.
 */

public abstract class BaseMainActivity extends FVT_Activity {
    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        ThemeManager.setStatusBarMode(this, true);
    }
}
