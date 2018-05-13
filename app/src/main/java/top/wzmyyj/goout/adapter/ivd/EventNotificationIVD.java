package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import top.wzmyyj.goout.R;
import top.wzmyyj.wzm_sdk.java.TimeUtil;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class EventNotificationIVD extends MyIVD<Message> {


    public EventNotificationIVD(Context context) {
        super(context);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_2;
    }

    @Override
    public boolean isForViewType(Message item, int position) {
        return item.getContentType() == ContentType.eventNotification;
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {
        MessageContent content = message.getContent();
        EventNotificationContent eventContent = (EventNotificationContent) content;
        String text = eventContent.getEventText().replace("群聊", "活动");
        holder.setText(R.id.tv_time,
                TimeUtil.changeToString(message.getCreateTime(), "MM-dd hh:mm:ss"))
                .setText(R.id.tv_name, "智能助手")
                .setText(R.id.tv_text, text);
        final ImageView img_head = holder.getView(R.id.img_head);

        img_head.setImageResource(R.drawable.ic_robot);

    }
}
