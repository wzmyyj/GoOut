package top.wzmyyj.goout.activity.my_info;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.my_info.panel.P_MyInfo;
import top.wzmyyj.goout.base.BaseSinglePanelActivity;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

public class MyInfoActivity extends BaseSinglePanelActivity {

    @NonNull
    @Override
    protected InitPanel getPanel() {
        return new P_MyInfo(activity);
    }

    @Override
    protected void setView(Toolbar toolbar, ImageView img_1, ImageView img_2) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_1.setImageResource(R.drawable.ic_write);
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, UpdateMyInfoActivity.class);
                context.startActivity(i);
                finish();
            }
        });
    }
}
