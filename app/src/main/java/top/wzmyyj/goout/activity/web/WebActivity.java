package top.wzmyyj.goout.activity.web;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.web.panel.P_Web;
import top.wzmyyj.goout.base.BaseSinglePanelActivity;
import top.wzmyyj.wzm_sdk.panel.InitPanel;
import top.wzmyyj.wzm_sdk.panel.WebPanel;

public class WebActivity extends BaseSinglePanelActivity {
    @NonNull
    @Override
    protected InitPanel getPanel() {
        Intent i = activity.getIntent();
        url = i.getStringExtra("url");
        return new P_Web(activity, url);
    }

    private String url;

    @Override
    protected void setView(Toolbar toolbar, ImageView img_1, ImageView img_2) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_1.setImageResource(R.drawable.ic_call_made);
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri u = Uri.parse(url);
                i.setData(u);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            WebPanel p = (WebPanel) mPanel;
            if (p.canGoBack()) {
                p.canGoBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
