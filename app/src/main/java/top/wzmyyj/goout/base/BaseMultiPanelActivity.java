package top.wzmyyj.goout.base;

import android.os.Bundle;

import top.wzmyyj.goout.tools.ThemeManager;
import top.wzmyyj.wzm_sdk.activity.MultiPanelActivity;

/**
 * Created by wzm on 2018/4/29 0029.
 */

public abstract class BaseMultiPanelActivity extends MultiPanelActivity {
    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        ThemeManager.setStatusBarMode(this, true);
    }
}
