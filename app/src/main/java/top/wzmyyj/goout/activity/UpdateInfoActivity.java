package top.wzmyyj.goout.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.tools.J;

public class UpdateInfoActivity extends BaseActivity {

    private Toolbar mToolbar;


    private LinearLayout ll_m_1;
    private LinearLayout ll_m_2;
    private LinearLayout ll_m_3;
    private LinearLayout ll_m_4;
    private LinearLayout ll_m_5;
    private LinearLayout ll_m_6;

    private ImageView img_m_1;
    private TextView tv_m_2;
    private TextView tv_m_3;
    private TextView tv_m_4;
    private TextView tv_m_5;
    private TextView tv_m_6;

    private LinearLayout ll_p_1;
    private LinearLayout ll_p_2;
    private LinearLayout ll_p_3;
    private LinearLayout ll_p_4;

    private TextView tv_p_2;
    private TextView tv_p_3;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_info);
        mToolbar = findViewById(R.id.toolbar);

        ll_m_1 = findViewById(R.id.ll_m_1);
        ll_m_2 = findViewById(R.id.ll_m_2);
        ll_m_3 = findViewById(R.id.ll_m_3);
        ll_m_4 = findViewById(R.id.ll_m_4);
        ll_m_5 = findViewById(R.id.ll_m_5);
        ll_m_6 = findViewById(R.id.ll_m_6);

        img_m_1 = findViewById(R.id.img_m_1);
        tv_m_2 = findViewById(R.id.tv_m_2);
        tv_m_3 = findViewById(R.id.tv_m_3);
        tv_m_4 = findViewById(R.id.tv_m_4);
        tv_m_5 = findViewById(R.id.tv_m_5);
        tv_m_6 = findViewById(R.id.tv_m_6);

        ll_p_1 = findViewById(R.id.ll_p_1);
        ll_p_2 = findViewById(R.id.ll_p_2);
        ll_p_3 = findViewById(R.id.ll_p_3);
        ll_p_4 = findViewById(R.id.ll_p_4);

        tv_p_2 = findViewById(R.id.tv_p_2);
        tv_p_3 = findViewById(R.id.tv_p_3);
    }

    @Override
    protected void initData() {
        UserInfo myInfo = JMessageClient.getMyInfo();
        setInfo(myInfo);
    }

    private void setInfo(UserInfo info) {
        if (info == null) return;
        tv_m_2.setText(info.getNickname());
        tv_m_3.setText(info.getSignature());
        tv_m_4.setText(J.getGender(info));
        tv_m_5.setText(J.getBirthday(info));
        tv_m_6.setText(info.getRegion());
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
