package top.wzmyyj.goout.fragment.f_2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.chat.CreateGroupChatActivity;
import top.wzmyyj.goout.activity.chat.DeleteGroupMemberActivity;
import top.wzmyyj.goout.activity.chat.GroupChatActivity;
import top.wzmyyj.goout.activity.chat.SingleChatActivity;
import top.wzmyyj.goout.activity.turing.OtherChatActivity;
import top.wzmyyj.goout.adapter.ivd.EmptyIVD;
import top.wzmyyj.goout.adapter.ivd.EventNotificationIVD;
import top.wzmyyj.goout.adapter.ivd.ImageIVD;
import top.wzmyyj.goout.adapter.ivd.ImportantTextIVD;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.goout.utils.gallery.GalleryUtil;
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
            headerData();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }, 100);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JMessageClient.exitConversation();
    }

    @NonNull
    @Override
    protected List<IVD<Message>> getIVD(List<IVD<Message>> ivd) {
        ivd.add(new EmptyIVD(context));
        ivd.add(new ImportantTextIVD(context));
        ivd.add(new ImageIVD(context));
        ivd.add(new EventNotificationIVD(context));
//        ivd.add(new LocationIVD());
//        ivd.add(new AccountsIVD());
//        ivd.add(new VoiceIVD());
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
        super.update();
        headerData();
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        layout.addView(menuView());
        layout.addView(startView());
    }


    //header


    private TextView tv_title;
    private Button bt_chat;
    private Button bt_1;
    private Button bt_2;
    private LinearLayout ll_member;
    private List<UserInfo> members;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_2_head, null);
        ll_member = header.findViewById(R.id.ll_1);
        tv_title = header.findViewById(R.id.tv_title);
        bt_chat = header.findViewById(R.id.bt_chat);
        bt_1 = header.findViewById(R.id.bt_h_1);
        bt_2 = header.findViewById(R.id.bt_h_2);
        headerData();
        headerListener();
        return header;
    }

    private void headerData() {
        members = new ArrayList<>();
        JMessageClient.getGroupInfo(ID, new GetGroupInfoCallback() {
            @Override
            public void gotResult(int i, String s, GroupInfo groupInfo) {
                if (i == 0) {
                    tv_title.setText(J.getName(groupInfo));
                    members.clear();
                    for (UserInfo user : groupInfo.getGroupMembers()) {
                        members.add(user);
                    }
                    setMemberList(members);
                }
            }
        });
    }

    private void setMemberList(List<UserInfo> members) {
        ll_member.removeAllViews();
        //robot
        setRobot();
        //member
        for (final UserInfo user : members) {
            View v = mInflater.inflate(R.layout.activity_group_chat_info_item, null);
            TextView tv = v.findViewById(R.id.tv_name);
            final ImageView img = v.findViewById(R.id.img_head);
            tv.setText(J.getName(user));
            user.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int i, String s, Bitmap bitmap) {
                    if (bitmap != null) {
                        img.setImageBitmap(bitmap);
                    } else {
                        img.setImageResource(R.mipmap.no_avatar);
                    }
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.putExtra("u", user.getUserName());
                    i.putExtra("n", J.getName(user));
                    i.setClass(context, SingleChatActivity.class);
                    context.startActivity(i);
                }
            });
            ll_member.addView(v);
        }

    }


    private void setRobot() {
        View v = mInflater.inflate(R.layout.activity_group_chat_info_item, null);
        TextView tv = v.findViewById(R.id.tv_name);
        final ImageView img = v.findViewById(R.id.img_head);
        tv.setText("助手");
        img.setImageResource(R.drawable.ic_robot);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, OtherChatActivity.class);
                context.startActivity(i);
            }
        });
        ll_member.addView(v);
    }

    private void headerListener() {
        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID != 0) {
                    Intent i = new Intent();
                    i.putExtra("id", ID);
                    i.putExtra("n", tv_title.getText().toString());
                    i.setClass(context, GroupChatActivity.class);
                    context.startActivity(i);
                }
            }
        });

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

    //footer
    private Button bt_exit;

    @Override
    protected View getFooter() {
        View footer = mInflater.inflate(R.layout.fragment_2_foot, null);
        bt_exit = footer.findViewById(R.id.bt_exit);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageDialog("确认退出？");
            }
        });
        return footer;
    }


    private void showMessageDialog(String msg) {
        new QMUIDialog.MessageDialogBuilder(activity)
                .setTitle("提示")
                .setMessage(msg)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "退出", QMUIDialogAction.ACTION_PROP_NEGATIVE,
                        new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                                deleteGroup(ID);
                            }
                        })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }

    private void deleteGroup(final long l) {
        changeWhenIDChange(0);
        JMessageClient.exitGroup(l, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    JMessageClient.exitConversation();
                    JMessageClient.deleteGroupConversation(l);
                    ContactsData.delGroup(l);
                    T.s("退出成功");
                } else {
                    T.s("退出失败");
                }
            }
        });
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
                        showEditDialog();
                        break;
                    case 2:
                        openGallery(true);
                        break;
                    case 3:
                        openGallery();
                        break;
                    case 4:
                        break;
                }
            }
        });
        return menu;
    }

    private void showEditDialog() {
        final QMUIDialog.EditTextDialogBuilder builder
                = new QMUIDialog.EditTextDialogBuilder(context);
        builder.setTitle("请输入：")
                .setPlaceholder("请输入公告")
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

                            sendText("公告：" + text.toString());
                            dialog.dismiss();
                        } else {
                        }
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }


    private void sendText(String text) {

        if (!TextUtils.isEmpty(text)) {
            Message message = JMessageClient.createGroupTextMessage(ID, text);
            message.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {
                        notifyDataSetChanged();
                    } else {
                        T.s("发送失败");
                        notifyDataSetChanged();
                    }
                }
            });
            JMessageClient.sendMessage(message);
            mData.add(message);
            notifyDataSetChanged();


//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    mList.setSelection(mAdapter.getCount());
//                }
//            }, 100);


        }
    }

    private void openGallery() {
        openGallery(false);
    }

    private void openGallery(boolean isOpenCamera) {
        GalleryUtil.initGallery(new IHandlerCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<String> photoList) {
                uploadPic(photoList.get(0));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError() {

            }
        }).getBuilder().crop(false).isOpenCamera(isOpenCamera).build();
        AndPermission.with(activity)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        GalleryUtil.open(activity);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        T.s("No Permission");
                    }
                })
                .start();
    }


    private void uploadPic(String picturePath) {
        if (picturePath != null) {
            File file = new File(picturePath);
            try {
                final Message message = JMessageClient.createGroupImageMessage(ID, file);
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            notifyDataSetChanged();
                        } else {
                            T.s("发送失败");
                            notifyDataSetChanged();
                        }
                    }
                });
                JMessageClient.sendMessage(message);
                mData.add(message);
                notifyDataSetChanged();
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mList.setSelection(mAdapter.getCount());
//                    }
//                }, 100);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
        }
    }


    /*






    hhhhhhhhhh







    */


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
        JMessageClient.createGroup(name, "!@#$%^&*()_+" + d, new CreateGroupCallback() {
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
