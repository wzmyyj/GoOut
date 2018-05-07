package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.view.LayoutInflater;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import top.wzmyyj.goout.R;
import top.wzmyyj.wzm_sdk.inter.IVD;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class LocationIVD implements IVD<Message> {

    private LayoutInflater mInflater;

    public LocationIVD(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_1;
    }

    @Override
    public boolean isForViewType(Message item, int position) {

        return item.getContentType() == ContentType.location;
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {

    }
}
