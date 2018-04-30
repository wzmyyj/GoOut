package top.wzmyyj.goout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import top.wzmyyj.goout.tools.ThemeManager;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeManager.setStatusBarTranslucent(this);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent();
                i.setClass(LaunchActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 1000);
    }
}
