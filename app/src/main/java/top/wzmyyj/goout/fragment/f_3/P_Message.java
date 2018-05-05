package top.wzmyyj.goout.fragment.f_3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.CreateGroupChatActivity;
import top.wzmyyj.goout.activity.chat.CreateSingleChatActivity;
import top.wzmyyj.goout.activity.chat.GroupChatActivity;
import top.wzmyyj.goout.activity.chat.SingleChatActivity;
import top.wzmyyj.goout.activity.contact.FindFriendActivity;
import top.wzmyyj.goout.activity.contact.NewFriendActivity;
import top.wzmyyj.goout.activity.message.SomeMessageActivity;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.Expression;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.adapter.CommonAdapter;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;

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
        ivd.add(new SingleIVD<Conversation>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.fragment_3_item;
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
                                        img_head.setImageResource(R.mipmap.no_avatar);
                                    }
                            }
                        });
                        break;

                }

            }
        });
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
                i.putExtra("flag", "m2");
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", "m3");
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", "m4");
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", "m5");
                i.setClass(context, SomeMessageActivity.class);
                context.startActivity(i);
            }
        });

        ll_h_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("flag", "m6");
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
                                    break;
                                case 1:
                                    intent.setClass(context, CreateGroupChatActivity.class);
                                    break;
                                case 2:
                                    intent.setClass(context, FindFriendActivity.class);
                                    break;
                                case 3:
                                    intent.setClass(context, FindFriendActivity.class);
                                    break;
                            }
                            context.startActivity(intent);
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
