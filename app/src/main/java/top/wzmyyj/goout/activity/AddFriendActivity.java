package top.wzmyyj.goout.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.wzm_sdk.tools.T;

public class AddFriendActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView img_1;
    private EditText et_1;
    private Button bt_1;
    private RecyclerView mRecyclerView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_friend);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        et_1 = findViewById(R.id.et_1);
        bt_1 = findViewById(R.id.bt_1);
        mRecyclerView = findViewById(R.id.recyclerView);
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
                String u = et_1.getText().toString();
                findUserInfo(u);
            }
        });
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("扫码");
            }
        });
    }

    private void findUserInfo(String username) {
    }
}
