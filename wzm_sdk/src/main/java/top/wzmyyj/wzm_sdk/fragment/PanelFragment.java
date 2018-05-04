package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public abstract class PanelFragment extends InitFragment {

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
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        for (InitPanel p : mPanelList) {
            p.initView();
        }
        return null;
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
    public void onStop() {
        super.onStop();
        for (InitPanel p : mPanelList) {
            p.onStop();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (InitPanel p : mPanelList) {
            p.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (InitPanel p : mPanelList) {
            p.onDestroyView();
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
