package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public abstract class PanelActivity extends InitActivity {

    protected List<InitPanel> mPanelList = new ArrayList<>();

    protected abstract List<InitPanel> getPanelList(List<InitPanel> mPanelList);

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPanelList.clear();
        mPanelList = getPanelList(mPanelList);
        for (InitPanel p : mPanelList) {
            p.initSome(savedInstanceState);
        }
    }

    @Override
    protected void initView() {
        for (InitPanel p : mPanelList) {
            p.initView();
        }
    }


    @Override
    protected void initData() {
        for (InitPanel p : mPanelList) {
            p.initData();
        }
    }


    @Override
    protected void initListener() {
        for (InitPanel p : mPanelList) {
            p.initListener();
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        for (InitPanel p : mPanelList) {
            p.initEvent();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (InitPanel p : mPanelList) {
            p.onResume();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (InitPanel p : mPanelList) {
            p.onStart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (InitPanel p : mPanelList) {
            p.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (InitPanel p : mPanelList) {
            p.onStop();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (InitPanel p : mPanelList) {
            p.onRestart();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (InitPanel p : mPanelList) {
            p.onDestroy();
        }
        mPanelList.clear();
    }
}
