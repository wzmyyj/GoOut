package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    protected void initSome(Bundle savedInstanceState) {
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();


    public void onResume() {

    }


    public void onStart() {

    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }


    public void onDestroy() {

    }

    public void onDestroyView() {

    }


}
