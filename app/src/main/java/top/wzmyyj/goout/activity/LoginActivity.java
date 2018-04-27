package top.wzmyyj.goout.activity;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;

public class LoginActivity extends BaseActivity{

    private EditText et_1;
    private EditText et_2;
    private ImageView img_2;
    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private CheckBox  ch_1;

    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        img_2 = findViewById(R.id.img_2);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
