package top.wzmyyj.goout.base;

import android.os.Bundle;

import top.wzmyyj.goout.tools.ThemeManager;
import top.wzmyyj.wzm_sdk.activity.InitActivity;

public abstract class BaseActivity extends InitActivity {
    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        ThemeManager.setStatusBarMode(this, true);


    }
}
