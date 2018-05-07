package top.wzmyyj.goout.adapter.ivd;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import top.wzmyyj.goout.R;
import top.wzmyyj.wzm_sdk.inter.IVD;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class ImportantTextIVD implements IVD<Message> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_1;
    }

    @Override
    public boolean isForViewType(Message item, int position) {
        if (item.getContentType() != ContentType.text) return false;
        String s = ((TextContent) item.getContent()).getText();
        return s.contains("！公告：");
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {

    }
}
