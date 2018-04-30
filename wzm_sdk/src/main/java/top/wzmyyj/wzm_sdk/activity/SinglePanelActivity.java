package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class SinglePanelActivity extends InitActivity {


    private LinearLayout layout;
    private Toolbar mToolbar;
    private ImageView img_1;
    private ImageView img_2;
    protected InitPanel mPanel;

    @Override
    protected void initView() {
        setContentView(R.layout.af_single_panel);
        layout = findViewById(R.id.ll_1);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        img_2 = findViewById(R.id.img_2);
        mPanel = getPanel();
        mPanel.initView();
        mToolbar.setTitle(mPanel.getTitle());
        setView(mToolbar, img_1, img_2);
    }

    protected abstract InitPanel getPanel();

    protected abstract void setView(Toolbar toolbar, ImageView img_1, ImageView img_2);


    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
    }

    @Override
    protected void initData() {
        mPanel.initData();
        layout.addView(mPanel.getView());
    }


    @Override
    protected void initListener() {
        mPanel.initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPanel.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mPanel.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
        mPanel.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPanel.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanel.onDestroy();
        mPanel = null;

    }


}
