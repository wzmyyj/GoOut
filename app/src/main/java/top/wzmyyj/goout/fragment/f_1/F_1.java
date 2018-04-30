package top.wzmyyj.goout.fragment.f_1;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseMultiPanelFragment;
import top.wzmyyj.wzm_sdk.panel.InitPanel;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_1 extends BaseMultiPanelFragment {


    //传入三个Panel
    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanelList.add(new P_1(getContext()));
        mPanelList.add(new P_2(getContext()));
        mPanelList.add(new P_3(getContext()));
        return mPanelList;
    }

    //给四个View设置属性
    @Override
    protected void setView(Toolbar toolbar, TabLayout tab, ViewPager vp, ImageView img) {
        img.setImageResource(R.drawable.ic_search);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("AAAAAAAAAAAAA");
            }
        });
    }

}
