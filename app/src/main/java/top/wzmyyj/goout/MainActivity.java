package top.wzmyyj.goout;


import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.activity.FPT_Activity;
import top.wzmyyj.goout.fragment.F_1;

public class MainActivity extends FPT_Activity {

    @Override
    protected List<FPT> getFPT() {
        List<FPT> mData = new ArrayList<>();
        mData.add(new FPT(new F_1(), "首页", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mData.add(new FPT(new F_1(), "第二", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mData.add(new FPT(new F_1(), "第三", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mData.add(new FPT(new F_1(), "我的", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return mData;
    }
}
