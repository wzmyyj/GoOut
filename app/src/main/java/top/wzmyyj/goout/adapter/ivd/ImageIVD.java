package top.wzmyyj.goout.adapter.ivd;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;

import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.tools.PhotoViewActivity;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class ImageIVD extends MyIVD<Message> {


    public ImageIVD(Context context) {
        super(context);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_2_item_3;
    }

    @Override
    public boolean isForViewType(Message item, int position) {
        return item.getContentType() == ContentType.image;
    }

    @Override
    public void convert(ViewHolder holder, Message message, int position) {
        final MessageContent content = message.getContent();
        final ImageContent imageContent = (ImageContent) content;

        ImageView img_image = holder.getView(R.id.img_image);

        Glide.with(mInflater.getContext())
                .load(imageContent.getLocalThumbnailPath())
                .centerCrop()
                .placeholder(R.mipmap.gallery_pick_photo)
                .into(img_image);

        UserInfo user = message.getFromUser();
        holder.setText(R.id.tv_time,
                TimeUtil.changeToString(message.getCreateTime(), "MM-dd hh:mm:ss"))
                .setText(R.id.tv_name, J.getName(user));
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

        final Message m = message;
        img_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageContent.downloadOriginImage(m, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        if (i == 0) {
                            Context context = mInflater.getContext();
                            Intent t = new Intent();
                            t.putExtra("path", file.getAbsolutePath());
                            t.setClass(context, PhotoViewActivity.class);
                            context.startActivity(t);
                        }
                    }
                });
            }
        });
    }
}
