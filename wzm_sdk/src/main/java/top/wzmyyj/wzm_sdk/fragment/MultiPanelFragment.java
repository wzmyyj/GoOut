package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class MultiPanelFragment extends InitFragment {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    protected List<InitPanel> mPanelList;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_multi_panel, container, false);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mImageView = view.findViewById(R.id.img_1);
        mPanelList = new ArrayList<>();
        mPanelList = getPanelList(mPanelList);
        for (InitPanel p : mPanelList) {
            p.initView();
        }
        setView(mTabLayout, mViewPager, mImageView);
        return view;
    }

    protected abstract List<InitPanel> getPanelList(List<InitPanel> mPanelList);

    protected abstract void setView(TabLayout tab, ViewPager vp, ImageView img);


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (InitPanel p : mPanelList) {
            p.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (InitPanel p : mPanelList) {
            p.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (InitPanel p : mPanelList) {
            p.onDestroyView();
        }
        mPanelList.clear();
    }
}
