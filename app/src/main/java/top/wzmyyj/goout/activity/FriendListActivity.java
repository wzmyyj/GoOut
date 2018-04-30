package top.wzmyyj.goout.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseSinglePanelActivity;
import top.wzmyyj.goout.panel.P_FriendList;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

public class FriendListActivity extends BaseSinglePanelActivity {


    @Override
    protected InitPanel getPanel() {
        return new P_FriendList(this);
    }

    @Override
    protected void setView(Toolbar toolbar, ImageView img_1, ImageView img_2) {
        toolbar.setTitle("好友列表");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
