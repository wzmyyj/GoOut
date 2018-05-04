package top.wzmyyj.wzm_sdk.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
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

public abstract class MultiPanelFragment extends PanelFragment {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;


    protected abstract List<InitPanel> getPanelList(List<InitPanel> mPanelList);

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        super.initView(inflater, container);
        View view = inflater.inflate(R.layout.af_multi_panel, container, false);
        mToolbar = view.findViewById(R.id.toolbar);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mImageView = view.findViewById(R.id.img_1);
        setView(mToolbar, mTabLayout, mViewPager, mImageView);
        return view;
    }

    protected abstract void setView(Toolbar toolbar, TabLayout tab, ViewPager vp, ImageView img);

    @Override
    protected void initData() {
        super.initData();
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


}
