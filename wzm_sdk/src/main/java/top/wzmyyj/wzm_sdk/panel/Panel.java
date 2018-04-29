package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public  class Panel {

    protected LayoutInflater mInflater;
    protected Context context;
    protected View view;
    protected String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Panel(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView() {
        return view;
    }

    public void onCreate(Bundle savedInstanceState){

    }

    public void onResume() {

    }


    public void onStart() {

    }

    public void onPause() {


    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }


    public void onDestroy() {

    }

    public void onDestroyView() {

    }


}
