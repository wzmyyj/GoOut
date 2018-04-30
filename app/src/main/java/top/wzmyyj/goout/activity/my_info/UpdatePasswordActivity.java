package top.wzmyyj.goout.activity.my_info;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.wzm_sdk.tools.T;

public class UpdatePasswordActivity extends BaseActivity {
    private Toolbar mToolbar;
    private EditText et_1;
    private EditText et_2;
    private Button bt_1;
    private Button bt_2;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_password);
        mToolbar = findViewById(R.id.toolbar);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("请联系开发者");
            }
        });
    }

    private void updatePassword() {
        final String oldPassword = et_1.getText().toString();
        final String newPassword = et_2.getText().toString();
        JMessageClient.updateUserPassword(oldPassword, newPassword,
                new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode,
                                          String updatePasswordDesc) {
                        if (responseCode == 0) {
                            T.s("update success");
                        } else {
                            T.s("update fail" + ":\n" + updatePasswordDesc);
                        }
                    }
                });
    }
}
