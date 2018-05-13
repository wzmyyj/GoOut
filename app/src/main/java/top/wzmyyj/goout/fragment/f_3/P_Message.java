package top.wzmyyj.goout.fragment.f_3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.CreateGroupChatActivity;
import top.wzmyyj.goout.activity.chat.CreateSingleChatActivity;
import top.wzmyyj.goout.activity.chat.GroupChatActivity;
import top.wzmyyj.goout.activity.chat.SingleChatActivity;
import top.wzmyyj.goout.activity.contact.FindFriendActivity;
import top.wzmyyj.goout.activity.contact.NewFriendActivity;
import top.wzmyyj.goout.activity.message.SomeMessageActivity;
import top.wzmyyj.goout.adapter.ivd.ConversationIVD;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.adapter.CommonAdapter;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_Message extends BaseRecyclerPanel<Conversation> {

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();


    public P_Message(Context context) {
        super(context);
        this.title = "消息";
    }

    @NonNull
    @Override
    protected List<Conversation> getData(List<Conversation> data) {
        if (JMessageClient.getConversationList() != null)
            for (Conversation conversation : JMessageClient.getConversationList()) {
                data.add(conversation);
            }
        return data;
    }


    @Override
    protected void update() {
        super.update();
        ContactsData.initFriendList();
        ContactsData.initGroupList();
    }

    @NonNull
    @Override
    protected List<IVD<Conversation>> getIVD(List<IVD<Conversation>> ivd) {
        ivd.add(new ConversationIVD(context));
        return ivd;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        Intent i = new Intent();
        Conversation conversation = mData.get(position - 1);
        switch (conversation.getType()) {
            case single:
                UserInfo user = (UserInfo) conversation.getTargetInfo();
                i.putExtra("u", user.getUserName());
                i.putExtra("n", J.getName(user));
                i.setClass(context, SingleChatActivity.class);
                context.startActivity(i);
                break;
            case group:
                GroupInfo group = (GroupInfo) conversation.getTargetInfo();
                i.putExtra("id", group.getGroupID());
                i.putExtra("n", J.getName(group));
                i.setClass(context, GroupChatActivity.class);
                context.startActivity(i);

                break;
        }

    }


    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        Conversation conversation = mData.get(position - 1);
        showMenuPopup(conversation, view);
        return true;
    }


    private void changeRead(Conversation conversation) {
        if (conversation.getUnReadMsgCnt() > 0) {
            conversation.resetUnreadCount();
        } else {
            conversation.setUnReadMessageCnt(1);
        }
        notifyDataSetChanged();

    }

    private void delConversation(Conversation conversation) {
        switch (conversation.getType()) {
            case single:
                UserInfo userInfo = (UserInfo) conversation.getTargetInfo();
                JMessageClient.deleteSingleConversation(userInfo.getUserName());
                break;
            case group:
                GroupInfo groupInfo = (GroupInfo) conversation.getTargetInfo();
                JMessageClient.deleteGroupConversation(groupInfo.getGroupID());
                break;
        }
        mData.remove(conversation);
        notifyDataSetChanged();
    }


    private void showMenuPopup(final Conversation conversation, View v) {
        List<String> data = new ArrayList<>();
        if (conversation.getUnReadMsgCnt() > 0) {
            data.add("标为已读");
            data.add("删除会话");
        } else {
            data.add("标为未读");
            data.add("删除会话");
        }

        final QMUIListPopup mPopup = new QMUIListPopup(context, QMUIPopup.DIRECTION_NONE,
                new CommonAdapter<String>(context, data, R.layout.popup_item) {
                    @Override
                    public void convert(ViewHolder holder, String bean, int position) {
                        holder.setText(R.id.tv_1, bean);
                    }
                });

        mPopup.create(QMUIDisplayHelper.dp2px(context, 130), QMUIDisplayHelper.dp2px(context, 200),
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mPopup.dismiss();
                        switch (i) {
                            case 0:
                                changeRead(conversation);
                                break;
                            case 1:
                                delConversation(conversation);
                                break;
                        }
                    }
                });
        mPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
        mPopup.show(v);

    }


    @Override
    public void onResume() {
        super.onResume();
        JMessageClient.registerEventReceiver(this);
    }


    public void onEvent(MessageEvent event) {
        handler.post(myRunnable);
    }


    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                update();
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        update();
    }

    @Override
    public void onPause() {
        super.onPause();
        JMessageClient.unRegisterEventReceiver(this);
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }


    private LinearLayout ll_h_1;
    private LinearLayout ll_h_2;
    private LinearLayout ll_h_3;
    private LinearLayout ll_h_4;
    private LinearLayout ll_h_5;
    private LinearLayout ll_h_6;
    private Button bt_h_1;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_3_head, null);
        ll_h_1 = header.findViewById(R.id.ll_h_1);
        ll_h_2 = header.findViewById(R.id.ll_h_2);
        ll_h_3 = header.findViewById(R.id.ll_h_3);
        ll_h_4 = header.findViewById(R.id.ll_h_4);
        ll_h_5 = header.findViewById(R.id.ll_h_5);
        ll_h_6 = header.findViewById(R.id.ll_h_6);
        bt_h_1 = header.findViewById(R.id.bt_h_1);
        headerData();
        headerListener();
        return header;
    }

    private void headerData() {

    }

    private void headerListener() {
        ll_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, NewFriendActivity.class);
                context.startActivity(i);
            }
        });
        ll_h_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", 2);
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", 3);
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", 4);
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", 5);
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", 6);
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });


        bt_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
                mListPopup.show(v);
            }
        });
    }


    private QMUIListPopup mListPopup;

    private void initListPopupIfNeed() {
        if (mListPopup == null) {
            String[] listItems = new String[]{
                    "发起单聊",
                    "发起群聊",
                    "添加好友",
                    "扫一扫"
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            mListPopup = new QMUIListPopup(context, QMUIPopup.DIRECTION_NONE,
                    new CommonAdapter<String>(context, data, R.layout.popup_item) {
                        @Override
                        public void convert(ViewHolder holder, String bean, int position) {
                            holder.setText(R.id.tv_1, bean);
                        }
                    });

            mListPopup.create(QMUIDisplayHelper.dp2px(context, 130), QMUIDisplayHelper.dp2px(context, 200),
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mListPopup.dismiss();
                            Intent intent = new Intent();
                            switch (i) {
                                case 0:
                                    intent.setClass(context, CreateSingleChatActivity.class);
                                    context.startActivity(intent);
                                    break;
                                case 1:
                                    intent.setClass(context, CreateGroupChatActivity.class);
                                    context.startActivity(intent);
                                    break;
                                case 2:
                                    intent.setClass(context, FindFriendActivity.class);
                                    context.startActivity(intent);
                                    break;
                                case 3:
                                    AndPermission.with(context)
                                            .permission(
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.CAMERA,
                                                    Manifest.permission.VIBRATE)
                                            .onGranted(new Action() {
                                                @Override
                                                public void onAction(List<String> permissions) {
                                                    Intent i = new Intent();
                                                    i.setClass(context, CaptureActivity.class);
                                                    context.startActivity(i);
                                                }
                                            })
                                            .onDenied(new Action() {
                                                @Override
                                                public void onAction(List<String> permissions) {
                                                    T.s("No Permission");
                                                }
                                            })
                                            .start();
                                    break;
                            }
                        }
                    });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
        }
    }


    private TextView tv_end;

    @Override
    protected View getFooter() {
        View footer = mInflater.inflate(R.layout.panel_footer, null);
        tv_end = footer.findViewById(R.id.tv_end);
        footerData();
        footerListener();
        return footer;
    }


    private void footerData() {
        if (mData.size() == 0) {
            tv_end.setText("--没有聊天消息--");
        } else {
            tv_end.setText("--end--");
        }
    }

    private void footerListener() {

    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        headerData();
        footerData();
    }
}
