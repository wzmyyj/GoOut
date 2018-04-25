package top.wzmyyj.goout.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;

public class UpdateInfoActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_info);
        mToolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void initData() {

//       this.setSupportActionBar(mToolbar);
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
