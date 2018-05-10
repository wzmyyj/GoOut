package top.wzmyyj.wzm_sdk.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class SinglePanelActivity extends PanelActivity {


    private LinearLayout layout;
    private Toolbar mToolbar;
    private ImageView img_1;
    private ImageView img_2;
    protected InitPanel mPanel;

    @NonNull
    protected abstract InitPanel getPanel();

    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanel = getPanel();
        mPanelList.add(mPanel);
        return mPanelList;
    }


    public void setTitle(String s) {
        if (mToolbar == null) return;
        this.mToolbar.setTitle(s);
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.af_single_panel);
        layout = findViewById(R.id.ll_1);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        img_2 = findViewById(R.id.img_2);
        mToolbar.setTitle(mPanel.getTitle());
        setView(mToolbar, img_1, img_2);
    }

    protected abstract void setView(Toolbar toolbar, ImageView img_1, ImageView img_2);

    @Override
    protected void initData() {
        super.initData();
        layout.addView(mPanel.getView());
    }
}
