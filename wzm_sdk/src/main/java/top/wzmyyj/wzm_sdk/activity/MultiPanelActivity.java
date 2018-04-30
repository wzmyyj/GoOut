package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class MultiPanelActivity extends InitActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    protected List<InitPanel> mPanelList;

    @Override
    protected void initView() {
        setContentView(R.layout.af_multi_panel);
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        mImageView = findViewById(R.id.img_1);
        mPanelList = new ArrayList<>();
        mPanelList = getPanelList(mPanelList);
        for (InitPanel p : mPanelList) {
            p.initView();
        }
        setView(mToolbar, mTabLayout, mViewPager, mImageView);
    }

    protected abstract List<InitPanel> getPanelList(List<InitPanel> mPanelList);

    protected abstract void setView(Toolbar toolbar, TabLayout tab, ViewPager vp, ImageView img);


    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
    }

    @Override
    protected void initData() {
        for (InitPanel p : mPanelList) {
            p.initData();
        }
        List<View> viewList = new ArrayList<View>();
        List<String> titles = new ArrayList<>();
        for (InitPanel p : mPanelList) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    protected void initListener() {
        for (InitPanel p : mPanelList) {
            p.initListener();
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
