package top.wzmyyj.goout.fragment;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BasePanelFragment;
import top.wzmyyj.goout.panel.P_1;
import top.wzmyyj.goout.panel.P_2;
import top.wzmyyj.goout.panel.P_3;
import top.wzmyyj.wzm_sdk.panel.InitPanel;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_1 extends BasePanelFragment {

    @Override
    protected View setView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mImageView = view.findViewById(R.id.img_1);
        return view;
    }

    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanelList.add(new P_1(getContext()));
        mPanelList.add(new P_2(getContext()));
        mPanelList.add(new P_3(getContext()));
        return mPanelList;
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
