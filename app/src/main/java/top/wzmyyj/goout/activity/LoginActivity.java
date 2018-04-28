package top.wzmyyj.goout.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.MainActivity;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.utils.BitmapLoader;
import top.wzmyyj.wzm_sdk.tools.T;

public class LoginActivity extends BaseActivity {

    private ImageView img_1;
    private EditText et_1;
    private EditText et_2;
    private ImageView img_2;
    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private CheckBox ch_1;

    private boolean run_login = false;

    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        if (JMessageClient.getMyInfo() != null) {
            Intent intent = new Intent();
            intent.setClass(context, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        img_1 = findViewById(R.id.img_1);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        img_2 = findViewById(R.id.img_2);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
        bt_3 = findViewById(R.id.bt_3);
        ch_1 = findViewById(R.id.ch_1);
    }

    @Override
    protected void initData() {

        et_2.setInputType(129);

        sha = getSharedPreferences("log", Activity.MODE_PRIVATE);
        ed = sha.edit();


        Intent i = getIntent();
        String username = i.getStringExtra("username");
        String password = i.getStringExtra("password");

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            et_1.setText(username);
            et_2.setText(password);
        } else {
            et_1.setText(sha.getString("username", ""));
            if (!TextUtils.isEmpty(sha.getString("password", ""))) {
                et_2.setText(sha.getString("password", ""));
                ch_1.setChecked(true);
            } else {
                ch_1.setChecked(false);
            }
        }
        String userAvatar = sha.getString("AvatarFile", "");
        Bitmap bitmap = BitmapLoader.getBitmapFromFile(userAvatar, 200, 200);
        if (bitmap != null) {
            img_1.setImageBitmap(bitmap);
        } else {
            img_1.setImageResource(R.mipmap.no_avatar);
        }

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
                if (run_login) return;
                String username = et_1.getText().toString();
                String password = et_2.getText().toString();
                run_login = true;
                login(username, password);
            }
        });


        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("请联系开发者");
            }
        });

        et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                img_1.setImageResource(R.mipmap.no_avatar);
            }
        });

    }


    private void login(final String username, final String password) {
        JMessageClient.login(username, password, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String LoginDesc) {
                if (responseCode == 0) {
                    T.l("login success");
                    ed.putString("username", username);
                    if (ch_1.isChecked()) {
                        ed.putString("password", password);
                    } else {
                        ed.putString("password", null);
                    }
                    File file = JMessageClient.getMyInfo().getAvatarFile();
                    if (file != null) {
                        ed.putString("AvatarFile", file.getAbsolutePath());
                    }
                    ed.commit();

                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    T.l("login fail" + ":\n" + LoginDesc);
                    run_login = false;
                }
            }
        });
    }


}
