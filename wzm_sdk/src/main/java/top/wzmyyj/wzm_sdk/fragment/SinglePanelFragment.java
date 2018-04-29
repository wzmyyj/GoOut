package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class SinglePanelFragment extends InitFragment {


    private LinearLayout layout;
    private Toolbar mToolbar;
    private ImageView img_1;
    private ImageView img_2;
    protected InitPanel mPanel;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_single_panel, container, false);
        layout = view.findViewById(R.id.ll_1);
        mToolbar = view.findViewById(R.id.toolbar);
        img_1 = view.findViewById(R.id.img_1);
        img_2 = view.findViewById(R.id.img_2);
        mPanel = getPanel();
        mPanel.initView();
        mToolbar.setTitle(mPanel.getTitle());
        setView(mToolbar, img_1, img_2);
        return view;
    }

    protected abstract InitPanel getPanel();

    protected abstract void setView(Toolbar mToolbar, ImageView img_1, ImageView img_2);


    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
    }

    @Override
    protected void initData() {
        mPanel.initData();
        layout.addView(mPanel.getView());
    }


    @Override
    protected void initListener() {
        mPanel.initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPanel.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mPanel.onStart();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPanel.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanel.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPanel.onDestroyView();
        mPanel = null;
    }

}
