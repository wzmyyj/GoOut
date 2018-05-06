package top.wzmyyj.goout.fragment.f_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.CreateGroupChatActivity;
import top.wzmyyj.goout.adapter.ivd.AccountsIVD;
import top.wzmyyj.goout.adapter.ivd.EventNotificationIVD;
import top.wzmyyj.goout.adapter.ivd.ImageIVD;
import top.wzmyyj.goout.adapter.ivd.ImportantTextIVD;
import top.wzmyyj.goout.adapter.ivd.LocationIVD;
import top.wzmyyj.goout.adapter.ivd.VoiceIVD;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.wzm_sdk.view.ArcMenu;

/**
 * Created by wzm on 2018/5/6 0006.
 */

public class P_Play extends BaseRecyclerPanel<Message> {
    public P_Play(Context context) {
        super(context);
        this.title = "活动";
    }

    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;
    private long ID;

    @Override
    public void initSome(Bundle savedInstanceState) {
        sha = activity.getSharedPreferences("play", Activity.MODE_PRIVATE);
        ed = sha.edit();
        ID = sha.getLong("id", 0);

        super.initSome(savedInstanceState);
    }

    @NonNull
    @Override
    protected List<Message> getData(List<Message> data) {
        Conversation conversation = JMessageClient.getGroupConversation(ID);
        if (conversation != null) {
            List<Message> messageList = conversation.getMessagesFromOldest(
                    conversation.getLatestMessage().getId() - 50, 50);
            for (Message message : messageList) {
                data.add(message);
            }
        }
        JMessageClient.enterGroupConversation(ID);
        return data;
    }


    private void changeWhenIDChange(long l) {
        if (ID == l) {
            return;
        }
        ID = l;
        ed.putLong("id", ID);
        ed.commit();
        setViewVisibility();
        if (ID > 0) {
            JMessageClient.enterGroupConversation(ID);
        } else {
            JMessageClient.exitConversation();
            mData.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        long id = sha.getLong("id", 0);
        changeWhenIDChange(id);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JMessageClient.exitConversation();
    }

    @NonNull
    @Override
    protected List<IVD<Message>> getIVD(List<IVD<Message>> ivd) {
        ivd.add(new ImportantTextIVD());
        ivd.add(new ImageIVD());
        ivd.add(new EventNotificationIVD());
        ivd.add(new LocationIVD());
        ivd.add(new AccountsIVD());
        ivd.add(new VoiceIVD());
        return ivd;
    }


    public void onEvent(MessageEvent event) {
        Message message = event.getMessage();
        if (message.getTargetType() != ConversationType.group) {
            return;
        }
        GroupInfo groupInfo = (GroupInfo) message.getTargetInfo();
        if (groupInfo.getGroupID() != ID) {
            return;
        }
        mData.add(message);
        handler.post(myRunnable);
    }

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                messageUpdate();
            } catch (Exception e) {

            }

        }
    }

    private void messageUpdate() {
        notifyDataSetChanged();
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        layout.addView(menuView());
        layout.addView(startView());
    }


    @Override
    protected View getHeader() {
        return null;
    }

    @Override
    protected View getFooter() {
        return null;
    }

    //menu
    private View menu;
    private ArcMenu arcMenu;

    private View menuView() {
        menu = mInflater.inflate(R.layout.fragment_2_menu, null);
        arcMenu = menu.findViewById(R.id.arcMenu);
        arcMenu.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                if (ID == 0) return;
                switch (pos) {
                    case 1:
                        changeWhenIDChange(0);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
        return menu;
    }


    //start
    private View start;
    private Button bt_start;
    private String type = "玩";


    private View startView() {
        start = mInflater.inflate(R.layout.fragment_2_start, null);
        bt_start = start.findViewById(R.id.bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextDialog();
            }
        });
        setViewVisibility();
        return start;
    }


    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder
                = new QMUIDialog.EditTextDialogBuilder(context);
        builder.setTitle("编辑：")
                .setPlaceholder("请输入活动名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            createGroup(text.toString(), type);
                            dialog.dismiss();
                        } else {
                        }
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }


    private void createGroup(String name, String d) {
        JMessageClient.createGroup(name, d, new CreateGroupCallback() {
            @Override
            public void gotResult(int i, String s, long l) {
                if (i == 0) {
                    changeWhenIDChange(l);
                    JMessageClient.getGroupInfo(l, new GetGroupInfoCallback() {
                        @Override
                        public void gotResult(int i, String s, GroupInfo groupInfo) {
                            if (i == 0) {
                                ContactsData.updateGroup(groupInfo);
                                Intent t = new Intent();
                                t.putExtra("id", ID);
                                t.putExtra("flag", 1);
                                t.setClass(context, CreateGroupChatActivity.class);
                                context.startActivity(t);
                            }
                        }
                    });
                } else {
                    T.s("创建失败" + "\n" + s);
                }
            }
        });
    }

    private void setViewVisibility() {
        if (ID == 0) {
            start.setVisibility(View.VISIBLE);
        } else {
            start.setVisibility(View.GONE);
        }
    }


}
