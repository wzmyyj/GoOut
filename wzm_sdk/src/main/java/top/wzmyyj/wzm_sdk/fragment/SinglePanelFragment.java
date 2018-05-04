package top.wzmyyj.wzm_sdk.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class SinglePanelFragment extends PanelFragment {


    private LinearLayout layout;
    private Toolbar mToolbar;
    private ImageView img_1;
    private ImageView img_2;
    protected InitPanel mPanel;

    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanel = getPanel();
        mPanelList.add(mPanel);
        return mPanelList;
    }

    protected abstract InitPanel getPanel();

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        super.initView(inflater, container);
        View view = inflater.inflate(R.layout.af_single_panel, container, false);
        layout = view.findViewById(R.id.ll_1);
        mToolbar = view.findViewById(R.id.toolbar);
        img_1 = view.findViewById(R.id.img_1);
        img_2 = view.findViewById(R.id.img_2);
        mToolbar.setTitle(mPanel.getTitle());
        setView(mToolbar, img_1, img_2);
        return view;
    }

    protected abstract void setView(Toolbar mToolbar, ImageView img_1, ImageView img_2);

    @Override
    protected void initData() {
        super.initData();
        layout.addView(mPanel.getView());
    }

}
