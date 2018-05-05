package top.wzmyyj.goout.activity.chat.panel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.CreateGroupChatActivity;
import top.wzmyyj.goout.activity.chat.DeleteGroupMemberActivity;
import top.wzmyyj.goout.activity.contact.UserInfoActivity;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;

import static top.wzmyyj.goout.R.id.bt_h_1;

/**
 * Created by wzm on 2018/5/5 0005.
 */

public class P_GroupChatInfo extends BaseRecyclerPanel<UserInfo> {
    public P_GroupChatInfo(Context context) {
        super(context);
        this.title = "群组信息";

    }

    private long ID;
    private GroupInfo group;

    @Override
    public void initSome(Bundle savedInstanceState) {
        Intent i = activity.getIntent();
        ID = i.getLongExtra("id", 0);
        if (ID == 0) {
            activity.finish();
        }
        super.initSome(savedInstanceState);
    }

    @NonNull
    @Override
    protected List<UserInfo> getData(List<UserInfo> data) {
        group = ContactsData.getGroup(ID);
        if (group != null) {
            for (UserInfo userInfo : group.getGroupMembers()) {
                data.add(userInfo);
            }
        }
        return data;
    }

    private void getGroupMember() {
        JMessageClient.getGroupInfo(ID, new GetGroupInfoCallback() {
            @Override
            public void gotResult(int i, String s, GroupInfo groupInfo) {
                if (i == 0) {
                    group = groupInfo;
                    ContactsData.updateGroup(group);
                    mData.clear();
                    for (UserInfo userInfo : group.getGroupMembers()) {
                        mData.add(userInfo);
                    }
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getGroupMember();
    }

    @NonNull
    @Override
    protected List<IVD<UserInfo>> getIVD(List<IVD<UserInfo>> ivd) {
        ivd.add(new SingleIVD<UserInfo>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.activity_group_chat_info_item;
            }

            @Override
            public void convert(ViewHolder holder, UserInfo userInfo, int position) {
                final ImageView img = holder.getView(R.id.img_head);
                TextView tv = holder.getView(R.id.tv_name);
                tv.setText(J.getName(userInfo));
                if (userInfo.getUserName().equals(group.getGroupOwner())) {
                    tv.setTextColor(context.getResources().getColor(R.color.colorOrange));
                }else{
                    tv.setTextColor(context.getResources().getColor(R.color.colorGray_7));
                }
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
            }
        });
        return ivd;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        Intent i = new Intent();
        i.putExtra("u", mData.get(position).getUserName());
        i.setClass(context, UserInfoActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new GridLayoutManager(context, 5));
    }

    private Button bt_1;
    private Button bt_2;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.activity_group_chat_info_head, null);
        bt_1 = header.findViewById(bt_h_1);
        bt_2 = header.findViewById(R.id.bt_h_2);
        headerData();
        headerListener();
        return header;
    }

    private void headerData() {

    }

    private void headerListener() {
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("id", ID);
                i.putExtra("flag", 1);
                i.setClass(context, CreateGroupChatActivity.class);
                context.startActivity(i);
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("id", ID);
                i.setClass(context, DeleteGroupMemberActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    protected View getFooter() {
        View footer = mInflater.inflate(R.layout.activity_group_chat_info_foot, null);
        return footer;
    }
}
