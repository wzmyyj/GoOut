package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.tools.Expression;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class ConversationIVD implements IVD<Conversation> {
    private LayoutInflater mInflater;

    public ConversationIVD(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.conversation_item;
    }

    @Override
    public boolean isForViewType(Conversation item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, Conversation conversation, int position) {
        final ImageView img_head = holder.getView(R.id.img_head);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_text = holder.getView(R.id.tv_text);
        TextView tv_count = holder.getView(R.id.tv_count);

        Message latestMessage = conversation.getLatestMessage();
        int unReadMsgCnt = conversation.getUnReadMsgCnt();
        String text = "", count = "", lastName = "";

        if (latestMessage != null) {
            long l = latestMessage.getCreateTime();
            tv_time.setText(TimeUtil.getEasyTime(l));
            MessageContent content = latestMessage.getContent();
            switch (content.getContentType()) {
                case text:
                    TextContent stringExtra = (TextContent) content;
                    text = stringExtra.getText();
                    break;
                case image:
                    text = "[图片]";
                    break;
                case voice:
                    text = "[语音]";
                    break;
                case video:
                    text = "[视频]";
                    break;
                case eventNotification:
                    EventNotificationContent eventContent = (EventNotificationContent) content;
                    text = eventContent.getEventText();
                    break;
            }

        }
        if (unReadMsgCnt > 0) {
            count = "" + unReadMsgCnt;
            tv_count.setText(count);
            tv_count.setBackgroundResource(R.drawable.ic_hip);
        } else {
            tv_count.setBackgroundResource(R.color.colorClarity);
        }
        if (latestMessage != null) {
            if (latestMessage.getFromUser().getUserName() ==
                    JMessageClient.getMyInfo().getUserName()) {
                lastName = "";
            } else {
                lastName = J.getName(latestMessage.getFromUser());
            }
        }
        int size = (int) tv_text.getTextSize();
        tv_text.setText(lastName + ": ");
        //解析qq表情
        SpannableString ss = Expression.getSpannableString(
                mInflater.getContext(), text, size + 5, size + 5);
        tv_text.append(ss);

        final int pos = position;
        switch (conversation.getType()) {
            case single:
                UserInfo user = (UserInfo) conversation.getTargetInfo();
                tv_name.setText(J.getName(user));
                img_head.setTag(pos);
                user.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (img_head.getTag() != null
                                && img_head.getTag().equals(pos))
                            if (bitmap != null) {
                                img_head.setImageBitmap(bitmap);
                            } else {
                                img_head.setImageResource(R.mipmap.no_avatar);
                            }
                    }
                });
                break;

            case group:
                GroupInfo group = (GroupInfo) conversation.getTargetInfo();
                tv_name.setText(J.getName(group));
                img_head.setTag(pos);
                group.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (img_head.getTag() != null
                                && img_head.getTag().equals(pos))
                            if (bitmap != null) {
                                img_head.setImageBitmap(bitmap);
                            } else {
                                img_head.setImageResource(R.mipmap.no_avatar_g);
                            }
                    }
                });
                break;

        }

    }
}
