package top.wzmyyj.goout.activity.message.panel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.bean.SomeMessage;
import top.wzmyyj.wzm_sdk.inter.IVD;

import static cn.jpush.android.api.JPushInterface.a.w;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class P_SomeMessage extends BaseRecyclerPanel<SomeMessage> {
    public P_SomeMessage(Context context) {
        super(context);
        this.title = "消息";
    }

    @Override
    public void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        Intent i = activity.getIntent();
        int W = i.getIntExtra("flag", 0);
        switch (w) {
            case 0:
            case 1:
                break;
            case 2:
                this.title = "关注";
                break;
            case 3:
                this.title = "喜欢和赞";
                break;
            case 4:
                this.title = "日程提示";
                break;
            case 5:
                this.title = "转账相关";
                break;
            case 6:
                this.title = "其他消息";
                break;
        }
    }

    @NonNull
    @Override
    protected List<SomeMessage> getData(List<SomeMessage> data) {
        return data;
    }

    @NonNull
    @Override
    protected List<IVD<SomeMessage>> getIVD(List<IVD<SomeMessage>> ivd) {
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }

    @Override
    protected View getHeader() {
        return null;
    }

    private TextView tv_end;

    @Override
    protected View getFooter() {
        View footer = mInflater.inflate(R.layout.panel_footer, null);
        tv_end = footer.findViewById(R.id.tv_end);
        footerData();
        footerListener();
        return footer;
    }


    private void footerData() {
        if (mData.size() == 0) {
            tv_end.setText("--暂无消息--");
        } else {
            tv_end.setText("--end--");
        }
    }

    private void footerListener() {

    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        footerData();
    }
}
