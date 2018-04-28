package top.wzmyyj.goout.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

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

    //给三个View设置属性
    @Override
    protected void setView(TabLayout tab, ViewPager vp, ImageView img) {
        img.setImageResource(R.drawable.ic_search);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("AAAAAAAAAAAAA");
            }
        });
    }

    //传入三个Panel
    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanelList.add(new P_1(getContext()));
        mPanelList.add(new P_2(getContext()));
        mPanelList.add(new P_3(getContext()));
        return mPanelList;
    }

}
