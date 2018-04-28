package top.wzmyyj.goout.adapter;

import android.view.View;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class MyPagerAdapter extends ViewTitlePagerAdapter {

    public MyPagerAdapter(List<View> viewList) {
        super(viewList);
    }

    public MyPagerAdapter(List<View> viewList, String[] titles) {
        super(viewList, titles);
    }

    public MyPagerAdapter(List<View> viewList, List<String> titles) {
        super(viewList, titles);
    }
}
