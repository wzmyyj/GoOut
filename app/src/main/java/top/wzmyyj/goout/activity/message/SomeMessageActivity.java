package top.wzmyyj.goout.activity.message;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.message.panel.P_SomeMessage;
import top.wzmyyj.goout.base.BaseSinglePanelActivity;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class SomeMessageActivity extends BaseSinglePanelActivity {
    @NonNull
    @Override
    protected InitPanel getPanel() {
        return new P_SomeMessage(activity);
    }

    @Override
    protected void setView(Toolbar toolbar, ImageView img_1, ImageView img_2) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
