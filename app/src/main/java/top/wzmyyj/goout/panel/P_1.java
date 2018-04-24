package top.wzmyyj.goout.panel;

import android.content.Context;
import android.widget.TextView;

import top.wzmyyj.goout.R;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_1 extends InitPanel {

    private TextView tv_1;

    public P_1(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        view = mInflater.inflate(R.layout.panel_f1_1, null);
        tv_1 = view.findViewById(R.id.tv_1);
    }

    @Override
    public void initData() {
        tv_1.setText("测试");
    }

    @Override
    public void initListener() {

    }
}
