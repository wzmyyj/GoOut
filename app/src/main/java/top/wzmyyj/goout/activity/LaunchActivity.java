package top.wzmyyj.goout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent();
        i.setClass(LaunchActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
}
