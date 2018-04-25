package top.wzmyyj.goout.panel;

import android.content.Context;
import android.widget.TextView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BasePanel;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_3 extends BasePanel {

    private TextView tv_1;

    public P_3(Context context) {
        super(context);
        this.title="商城";
    }

    @Override
    public void initView() {
        view = mInflater.inflate(R.layout.panel_3, null);
        tv_1 = view.findViewById(R.id.tv_1);
    }

    @Override
    public void initData() {
        tv_1.setText("测试3");
    }

    @Override
    public void initListener() {

    }
}
