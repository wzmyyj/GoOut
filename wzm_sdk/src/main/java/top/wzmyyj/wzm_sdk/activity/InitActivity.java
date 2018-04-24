package top.wzmyyj.wzm_sdk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSome(savedInstanceState);
        initView();
        initData();
        initListener();
        initEvent();
    }

    protected void initSome(Bundle savedInstanceState) {
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected void initEvent() {
    }


}
