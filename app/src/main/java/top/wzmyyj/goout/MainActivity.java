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
                .add(new F_1(), "主页", R.mipmap.ic_home_0, R.mipmap.ic_home_1)
                .add(new F_2(), "活动", R.mipmap.ic_timeline_0, R.mipmap.ic_timeline_1)
                .add(new F_3(), "消息", R.mipmap.ic_message_0, R.mipmap.ic_message_1)
                .add(new F_4(), "我的", R.mipmap.ic_mine_0, R.mipmap.ic_mine_1);
        return fpt;
    }
}
