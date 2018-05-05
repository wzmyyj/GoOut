package top.wzmyyj.goout.activity.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.panel.P_DeleteGroupMember;
import top.wzmyyj.goout.base.BaseSinglePanelActivity;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

public class DeleteGroupMemberActivity extends BaseSinglePanelActivity {


    @NonNull
    @Override
    protected InitPanel getPanel() {
        return new P_DeleteGroupMember(activity);
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
