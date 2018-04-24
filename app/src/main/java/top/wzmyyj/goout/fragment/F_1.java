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
import top.wzmyyj.goout.panel.P_1;
import top.wzmyyj.wzm_sdk.fragment.InitFragment;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_1 extends InitFragment {

    private ViewPager mViewPager;
    private P_1 mP_1;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_x, container, false);
        mViewPager = view.findViewById(R.id.viewPager);

        mP_1=new P_1(inflater.getContext());
        mP_1.initView();
        View view1=inflater.inflate(R.layout.panel_f1_1,null);
        List<View> viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view1);
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(viewList,new String[2]);
        mViewPager.setAdapter(pagerAdapter);


        return view;
    }

    @Override
    protected void initData() {
        mP_1.initData();


    }

    @Override
    protected void initListener() {

    }
}
