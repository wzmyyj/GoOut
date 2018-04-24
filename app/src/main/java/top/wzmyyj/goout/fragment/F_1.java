package top.wzmyyj.goout.fragment;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.adapter.MyPagerAdapter;
import top.wzmyyj.goout.base.BaseFragment;
import top.wzmyyj.goout.panel.P_1;
import top.wzmyyj.goout.panel.P_2;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_1 extends BaseFragment {

    private ViewPager mViewPager;
    private P_1 mP_1;
    private P_2 mP_2;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mViewPager = view.findViewById(R.id.viewPager);

        mP_1 = new P_1(inflater.getContext());
        mP_2 = new P_2(inflater.getContext());

        mP_1.initView();
        mP_2.initView();

        return view;
    }

    @Override
    protected void initData() {
        mP_1.initData();
        mP_2.initData();
        List<View> viewList = new ArrayList<View>();
        viewList.add(mP_1.getView());
        viewList.add(mP_2.getView());
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(viewList);
        mViewPager.setAdapter(pagerAdapter);


    }

    @Override
    protected void initListener() {

    }
}
