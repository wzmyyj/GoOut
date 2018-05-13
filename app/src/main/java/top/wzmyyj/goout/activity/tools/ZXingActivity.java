package top.wzmyyj.goout.activity.tools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;

public class ZXingActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_zxing);


    }

    @Override
    protected void initData() {
        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
                resultIntent.putExtras(bundle);
                activity.setResult(RESULT_OK, resultIntent);
                activity.finish();
            }

            @Override
            public void onAnalyzeFailed() {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                activity.setResult(RESULT_OK, resultIntent);
                activity.finish();
            }
        };

//        CaptureFragment captureFragment = new CaptureFragment();
//        // 为二维码扫描界面设置定制化界面
//        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
//        captureFragment.setAnalyzeCallback(analyzeCallback);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initListener() {

    }
}
