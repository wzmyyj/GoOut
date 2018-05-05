package top.wzmyyj.goout.activity.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.contact.UserInfoActivity;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.tools.T;

public class CreateGroupChatActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView img_1;
    private RecyclerView mRV_1;
    private RecyclerView mRecyclerView;
    private List<UserInfo> mData, mHead;
    private CommonAdapter mAdapter, mHeadAdapter;
    private long ID = 0;
    private int flag = 0;
    private String n = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_creat_group_chat);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        mRV_1 = findViewById(R.id.rv_1);
        mRecyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    protected void initData() {
        mHead = new ArrayList<>();
        mData = new ArrayList<>();
        mData = ContactsData.getFriendList();
        Intent i = getIntent();
        ID = i.getLongExtra("id", 0);
        flag = i.getIntExtra("flag", 0);
        GroupInfo group = ContactsData.getGroup(ID);
        if (group != null) {
            n = J.getName(group);
            List<UserInfo> list = new ArrayList<>();
            for (UserInfo userInfo : mData) {
                for (UserInfo member : group.getGroupMembers()) {
                    if (userInfo.getUserName().equals(member.getUserName())) {
                        list.add(userInfo);
                        continue;
                    }
                }
            }

            for (UserInfo userInfo : list) {
                mData.remove(userInfo);
            }
        }

        //mRV_1

        mRV_1.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
        mHeadAdapter = new CommonAdapter<UserInfo>(context, R.layout.head_item, mHead) {
            @Override
            protected void convert(ViewHolder holder, UserInfo userInfo, int position) {
                final ImageView img = holder.getView(R.id.img_head);
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
        };
        mRV_1.setAdapter(mHeadAdapter);

        //mRecyclerView

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mAdapter = new CommonAdapter<UserInfo>(context,
                R.layout.friend_list_item, mData) {
            @Override
            protected void convert(ViewHolder holder, UserInfo userInfo, int position) {
                final ImageView img = holder.getView(R.id.img_1);
                TextView tv = holder.getView(R.id.tv_1);
                Button bt = holder.getView(R.id.bt_1);
                final UserInfo user = userInfo;
                if (!mHead.contains(userInfo)) {
                    bt.setText("+");
                    bt.setTextColor(context.getResources().getColor(R.color.colorGreen));
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHead.add(user);
                            mHeadAdapter.notifyDataSetChanged();
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    bt.setText("-");
                    bt.setTextColor(context.getResources().getColor(R.color.colorRed));
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHead.remove(user);
                            mHeadAdapter.notifyDataSetChanged();
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
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
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.putExtra("u", user.getUserName());
                        i.putExtra("n", J.getName(user));
                        i.setClass(context, UserInfoActivity.class);
                        startActivity(i);
                    }
                });

            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }


    private boolean is_run = false;

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHead.size() == 0) {
                    T.s("请添加");
                    return;
                }
                if (is_run) return;
                if (ID == 0) {
                    showEditTextDialog();
                } else {
                    addGroupMembers(ID);
                }
            }
        });
    }


    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder
                = new QMUIDialog.EditTextDialogBuilder(context);
        builder.setTitle("编辑：")
                .setPlaceholder("请输入群组名称")
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
                            createGroup(text.toString());
                            dialog.dismiss();
                        } else {
                        }
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }


    private void createGroup(String name) {
        is_run = true;
        JMessageClient.createGroup(name,
                "由用户：" + J.getName(JMessageClient.getMyInfo()) + "创建",
                new CreateGroupCallback() {
                    @Override
                    public void gotResult(int i, String s, long l) {
                        is_run = false;
                        if (i == 0) {
                            ID = l;
                            addGroupMembers(ID);
                        }
                    }
                });
    }

    private void addGroupMembers(final long id) {
        is_run = true;
        List<String> list = new ArrayList<>();
        for (UserInfo userInfo : mHead) {
            list.add(userInfo.getUserName());
        }
        JMessageClient.addGroupMembers(id, list, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                is_run = false;
                if (i == 0) {
                    T.s("添加成功");
                    if (flag == 1) {
                        activity.finish();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.putExtra("n", n);
                    intent.setClass(context, GroupChatActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    T.s("添加失败");
                }
            }
        });
    }
}
