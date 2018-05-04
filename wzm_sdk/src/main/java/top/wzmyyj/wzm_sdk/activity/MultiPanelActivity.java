package top.wzmyyj.wzm_sdk.activity;

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

public abstract class MultiPanelActivity extends PanelActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.af_multi_panel);
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        mImageView = findViewById(R.id.img_1);
        setView(mToolbar, mTabLayout, mViewPager, mImageView);
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
