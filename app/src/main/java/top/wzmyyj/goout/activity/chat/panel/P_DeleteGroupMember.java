package top.wzmyyj.goout.activity.chat.panel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/5/5 0005.
 */

public class P_DeleteGroupMember extends BaseRecyclerPanel<UserInfo> {

    public P_DeleteGroupMember(Context context) {
        super(context);
        this.title = "删除成员";
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
            return data;
        }
        for (UserInfo userInfo : group.getGroupMembers()) {
            data.add(userInfo);
        }
        return data;
    }

    @NonNull
    @Override
    protected List<IVD<UserInfo>> getIVD(List<IVD<UserInfo>> ivd) {
        ivd.add(new SingleIVD<UserInfo>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.friend_list_item;
            }

            @Override
            public void convert(ViewHolder holder, UserInfo userInfo, int position) {
                final ImageView img = holder.getView(R.id.img_1);
                TextView tv = holder.getView(R.id.tv_1);
                tv.setText(J.getName(userInfo));
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
                Button bt = holder.getView(R.id.bt_1);
                bt.setVisibility(View.VISIBLE);
                if (JMessageClient.getMyInfo().getUserName().equals(userInfo.getUserName())) {
                    bt.setText("我");
                    bt.setTextColor(context.getResources().getColor(R.color.colorBlue));
                } else {
                    bt.setText("-");
                    bt.setTextColor(context.getResources().getColor(R.color.colorRed));
                    final int p = position;
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessageNegativeDialog(p);
                        }
                    });
                }
            }
        });
        return ivd;
    }


    private void showMessageNegativeDialog(final int p) {
        new QMUIDialog.MessageDialogBuilder(activity)
                .setTitle("标题")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE,
                        new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                deleteMember(p);
                                dialog.dismiss();
                            }
                        })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }

    private void deleteMember(final int p) {
        List<String> list = new ArrayList<>();
        list.add(mData.get(p).getUserName());
        JMessageClient.removeGroupMembers(ID, list, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    T.s("删除成功");
                    JMessageClient.getGroupInfo(ID, new GetGroupInfoCallback() {
                        @Override
                        public void gotResult(int i, String s, GroupInfo groupInfo) {
                            if (i == 0) {
                                ContactsData.updateGroup(groupInfo);
                            }
                        }
                    });
                    mData.remove(p);
                    notifyDataSetChanged();
                } else {
                    T.s("删除失败：" + "\n" + s);
                }
            }
        });
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
