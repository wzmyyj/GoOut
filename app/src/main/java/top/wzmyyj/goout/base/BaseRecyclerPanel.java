package top.wzmyyj.goout.base;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.RecyclerPanel;

/**
 * Created by wzm on 2018/4/29 0029.
 */

public abstract class BaseRecyclerPanel<T> extends RecyclerPanel<T> {
    public BaseRecyclerPanel(Context context) {
        super(context);
    }
}
