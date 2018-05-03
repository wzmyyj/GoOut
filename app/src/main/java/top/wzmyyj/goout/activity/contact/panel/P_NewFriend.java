package top.wzmyyj.goout.activity.contact.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.database.NewFriend;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/5/3 0003.
 */

public class P_NewFriend extends BaseRecyclerPanel<UserInfo> {


    public P_NewFriend(Context context) {
        super(context);
        this.title = "新朋友";
    }

    @NonNull
    @Override
    protected List<UserInfo> getData(List<UserInfo> data) {
        try {
            List<NewFriend> execute = new Select().from(NewFriend.class).execute();
            for (final NewFriend nf : execute) {
                if (nf == null) continue;
                JMessageClient.getUserInfo(nf.getUsername(), new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        if (userInfo != null) {
                            mData.add(userInfo);
                            userInfo.setNoteText(nf.getReason());
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        } catch (Exception e) {
            L.e(e.getMessage());
        }
        return data;
    }

    @NonNull
    @Override
    protected List<IVD<UserInfo>> getIVD(List<IVD<UserInfo>> ivd) {
        ivd.add(new SingleIVD<UserInfo>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.new_friend_item;
            }

            @Override
            public void convert(ViewHolder holder, UserInfo userInfo, int position) {
                final ImageView img = holder.getView(R.id.img_head);
                TextView tv_name = holder.getView(R.id.tv_name);
                TextView tv_reason = holder.getView(R.id.tv_reason);
                tv_name.setText(J.getName(userInfo));
                tv_reason.setText(userInfo.getNoteText());
                userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (bitmap != null) {
                            img.setImageBitmap(bitmap);
                        } else {
                            img.setImageResource(R.mipmap.no_avatar);
                        }
                    }
                });
                Button bt_1 = holder.getView(R.id.bt_1);
                Button bt_2 = holder.getView(R.id.bt_2);

                final String username = userInfo.getUserName();
                bt_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContactManager.acceptInvitation(username, null,
                                new BasicCallback() {
                                    @Override
                                    public void gotResult(int i, String s) {
                                        if (i == 0) {
                                            JMessageClient.getUserInfo(username, new GetUserInfoCallback() {
                                                @Override
                                                public void gotResult(int i, String s, UserInfo userInfo) {
                                                    if (i == 0) {
                                                        ContactsData.addFriend(userInfo);
                                                    }
                                                }
                                            });
                                            new Delete()
                                                    .from(NewFriend.class)
                                                    .where("username = ?", username)
                                                    .execute();
                                            update();
                                            T.s("添加成功！");
                                        } else {
                                            T.s("添加失败！");
                                        }
                                    }
                                });
                    }
                });
                bt_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContactManager.declineInvitation(username, null, null, new BasicCallback() {
                            @Override
                            public void gotResult(int i, String s) {
                                if (i == 0) {
                                    new Delete()
                                            .from(NewFriend.class)
                                            .where("username = ?", username)
                                            .execute();
                                    update();
                                    T.s("发送成功！");
                                } else {
                                    T.s("发送失败！");
                                }
                            }
                        });
                    }
                });
            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }


    @Override
    protected View getHeader() {
        return null;
    }

    @Override
    protected View getFooter() {
        return null;
    }


}
