package top.wzmyyj.goout.base;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.WebPanel;

/**
 * Created by wzm on 2018/5/9 0009.
 */

public abstract class BaseWebPanel extends WebPanel {

    public BaseWebPanel(Context context) {
        super(context);
    }

    public BaseWebPanel(Context context, String url) {
        super(context, url);
    }
}
