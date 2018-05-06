package top.wzmyyj.goout.fragment.f_2;


import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseSinglePanelFragment;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_2 extends BaseSinglePanelFragment {


    @Override
    protected InitPanel getPanel() {
        return new P_Play(activity);
    }

    @Override
    protected void setView(Toolbar mToolbar, ImageView img_1, ImageView img_2) {
        img_1.setImageResource(R.drawable.ic_search);
        img_2.setImageResource(R.drawable.ic_fullscreen);
    }
}
