package top.wzmyyj.goout;


import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import top.wzmyyj.goout.base.BaseMainActivity;
import top.wzmyyj.goout.fragment.F_1;
import top.wzmyyj.goout.fragment.F_2;
import top.wzmyyj.goout.fragment.F_3;
import top.wzmyyj.goout.fragment.F_4;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    @Override
    protected FPT getFPT(FPT fpt) {
        fpt
                .add(new F_1(), "活动", R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .add(new F_2(), "发现", R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .add(new F_3(), "消息", R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .add(new F_4(), "我的", R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        return fpt;
    }
}
