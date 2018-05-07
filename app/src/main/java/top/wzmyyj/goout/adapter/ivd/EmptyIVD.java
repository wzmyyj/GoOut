package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import top.wzmyyj.goout.R;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class EmptyIVD extends MyIVD<Message> {


    public EmptyIVD(Context context) {
        super(context);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_0;
    }

    @Override
    public boolean isForViewType(Message item, int position) {
        if (item.getContentType() != ContentType.text) {
            return false;
        }
        MessageContent content = item.getContent();
        TextContent textContent = (TextContent) content;
        String text = textContent.getText();
        if (text.startsWith("公告：")) {
            return false;
        }
        return true;
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {

    }

}
