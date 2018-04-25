package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public abstract class InitPanel {

    protected LayoutInflater mInflater;
    protected View view;
    protected String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InitPanel(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView() {
        return view;
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();




}
