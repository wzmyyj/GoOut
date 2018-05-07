package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

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

        UserInfo user = message.getFromUser();
        holder.setText(R.id.tv_time,
                TimeUtil.changeToString(message.getCreateTime(), "MM-dd hh:mm:ss"))
                .setText(R.id.tv_name, J.getName(user))
                .setText(R.id.tv_text, text);
        final ImageView img_head = holder.getView(R.id.img_head);
        user.getAvatarBitmap(new GetAvatarBitmapCallback() {
            @Override
            public void gotResult(int i, String s, Bitmap bitmap) {

                if (bitmap != null) {
                    img_head.setImageBitmap(bitmap);
                } else {
                    img_head.setImageResource(R.mipmap.no_avatar);
                }
            }
        });

    }
}
