package top.wzmyyj.goout.activity.chat.panel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.wzm_sdk.inter.IVD;

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
        if (group == null) {
            getGroup();
            return data;
        }
        for (UserInfo userInfo : group.getGroupMembers()) {
            data.add(userInfo);
        }
        return data;
    }

    private void getGroup() {
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

    @NonNull
    @Override
    protected List<IVD<UserInfo>> getIVD(List<IVD<UserInfo>> ivd) {
        return null;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new GridLayoutManager(context, 4));
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
