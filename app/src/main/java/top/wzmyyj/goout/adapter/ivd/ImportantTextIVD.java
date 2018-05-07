package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class ImportantTextIVD extends MyIVD<Message> {


    public ImportantTextIVD(Context context) {
        super(context);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_1;
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
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {
        MessageContent content = message.getContent();
        TextContent textContent = (TextContent) content;
        String text = textContent.getText();

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
