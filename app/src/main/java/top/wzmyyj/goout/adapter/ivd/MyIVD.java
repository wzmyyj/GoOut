package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.view.LayoutInflater;

import top.wzmyyj.wzm_sdk.inter.IVD;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public abstract class MyIVD<T> implements IVD<T> {
    protected LayoutInflater mInflater;

    public MyIVD(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

}
