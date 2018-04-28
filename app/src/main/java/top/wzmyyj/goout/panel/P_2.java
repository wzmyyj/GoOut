package top.wzmyyj.goout.panel;

import android.content.Context;
import android.widget.TextView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BasePanel;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_2 extends BasePanel {

    private TextView tv_1;

    public P_2(Context context) {
        super(context);
        this.title = "周边";
    }

    @Override
    public void initView() {
        view = mInflater.inflate(R.layout.panel_2, null);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
