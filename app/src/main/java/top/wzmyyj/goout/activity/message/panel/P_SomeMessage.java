package top.wzmyyj.goout.activity.message.panel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.bean.SomeMessage;
import top.wzmyyj.wzm_sdk.inter.IVD;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class P_SomeMessage extends BaseRecyclerPanel<SomeMessage> {
    public P_SomeMessage(Context context) {
        super(context);
        this.title = "消息";
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

    @Override
    protected View getFooter() {
        return null;
    }
}
