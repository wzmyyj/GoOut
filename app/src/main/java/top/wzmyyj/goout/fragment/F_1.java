package top.wzmyyj.goout.fragment;


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

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.adapter.MyPagerAdapter;
import top.wzmyyj.goout.base.BaseFragment;
import top.wzmyyj.goout.panel.P_1;
import top.wzmyyj.goout.panel.P_2;
import top.wzmyyj.goout.panel.P_3;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_1 extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private P_1 mP_1;
    private P_2 mP_2;
    private P_3 mP_3;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mImageView = view.findViewById(R.id.img_1);

        mP_1 = new P_1(inflater.getContext());
        mP_2 = new P_2(inflater.getContext());
        mP_3 = new P_3(inflater.getContext());

        mP_1.initView();
        mP_2.initView();
        mP_3.initView();

        return view;
    }

    @Override
    protected void initData() {
        mP_1.initData();
        mP_2.initData();
        mP_3.initData();

        List<View> viewList = new ArrayList<View>();
        viewList.add(mP_1.getView());
        viewList.add(mP_2.getView());
        viewList.add(mP_3.getView());
        String[] titles = new String[]{
                mP_1.getTitle(), mP_2.getTitle(), mP_3.getTitle()};
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected void initListener() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("SSSSSSS");
            }
        });
    }
}
