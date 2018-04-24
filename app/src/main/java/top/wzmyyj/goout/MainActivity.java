package top.wzmyyj.goout;


import top.wzmyyj.goout.base.BaseMainActivity;
import top.wzmyyj.goout.fragment.F_1;
import top.wzmyyj.goout.fragment.F_2;
import top.wzmyyj.goout.fragment.F_3;
import top.wzmyyj.goout.fragment.F_4;

public class MainActivity extends BaseMainActivity {


    @Override
    protected FPT getFPT(FPT fpt) {
        fpt
                .add(new F_1(), "主页", R.mipmap.homepage, R.mipmap.homepage_fill)
                .add(new F_2(), "活动", R.mipmap.service, R.mipmap.service_fill)
                .add(new F_3(), "消息", R.mipmap.remind, R.mipmap.remind_fill)
                .add(new F_4(), "我的", R.mipmap.people, R.mipmap.people_fill);
        return fpt;
    }
}
