package top.wzmyyj.goout.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.wzm_sdk.tools.T;

public class RegisterActivity extends BaseActivity {

    private EditText et_1;
    private EditText et_2;
    private ImageView img_2;
    private Button bt_1;
    private Button bt_2;

    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        img_2 = findViewById(R.id.img_2);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
    }

    @Override
    protected void initData() {
        et_2.setInputType(129);
        sha = getSharedPreferences("log", Activity.MODE_PRIVATE);
        ed = sha.edit();
    }

    @Override
    protected void initListener() {
        img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_2.getInputType() == 129) {
                    img_2.setImageResource(R.mipmap.ic_eye_1);
                    et_2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else if (et_2.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    img_2.setImageResource(R.mipmap.ic_eye_0);
                    et_2.setInputType(129);
                }
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = et_2.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_1.getText().toString();
                String password = et_2.getText().toString();
                register(username, password);
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void register(final String username, final String password) {
        JMessageClient.register(username, password,
                new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String registerDesc) {
                        if (responseCode == 0) {
                            T.l("register success");
                            login(username, password);
                        } else {
                            T.l("register fail" + ":\n" + registerDesc);
                        }
                    }
                });
    }

    private void login(final String username, String password) {
        ed.putString("username", username);
        ed.commit();
        JMessageClient.login(username, password, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String LoginDesc) {
                if (responseCode == 0) {
                    getUserInfo(username);
                } else {
                    T.l("login fail" + ":\n" + LoginDesc);
                    Intent intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void getUserInfo(final String username) {
        JMessageClient.getUserInfo(username, null,
                new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String msg, UserInfo userInfo) {
                        if (i == 0) {
                            T.l("login success");
                            Intent intent = new Intent();
                            intent.setClass(context, InitInfoActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            T.l("login fail" + ":\n" + msg);
                            JMessageClient.logout();
                            Intent intent = new Intent();
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                });
    }
}
