package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public abstract class InitPanel extends Panel {

    public InitPanel(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSome(savedInstanceState);
        initData();
        initListener();
        initEvent();
    }

    public void initSome(Bundle savedInstanceState) {
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    public void initEvent() {
    }
}
