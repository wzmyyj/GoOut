package top.wzmyyj.goout.activity.contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.my_info.MyInfoActivity;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.data.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.tools.T;

public class FindFriendActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView img_1;
    private EditText et_1;
    private Button bt_1;
    private RecyclerView mRecyclerView;
    private List<UserInfo> mData;
    private CommonAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_find_friend);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        et_1 = findViewById(R.id.et_1);
        bt_1 = findViewById(R.id.bt_1);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mAdapter = new CommonAdapter<UserInfo>(context,
                R.layout.friend_list_item, mData) {
            @Override
            protected void convert(ViewHolder holder, UserInfo userInfo, int position) {
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
                if (userInfo.isFriend() ||
                        JMessageClient.getMyInfo().getUserName().equals(userInfo.getUserName())) {
                    bt.setText("");
                } else {
                    bt.setText("+好友");
                    final String username = userInfo.getUserName();
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showEditTextDialog(username);
                        }
                    });
                }

            }
        };

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private void showEditTextDialog(final String username) {
        final QMUIDialog.EditTextDialogBuilder builder
                = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("编辑：")
                .setPlaceholder("填写申请理由")
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
                            sendInvitation(username, text.toString());
                            dialog.dismiss();
                        } else {

                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }


    private void sendInvitation(String username, String reason) {
        ContactManager.sendInvitationRequest(username, null, reason,
                new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            T.s("请求成功");
                        } else {
                            T.s("请求失败");
                        }
                    }
                });
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
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_run) return;
                is_run = true;
                String u = et_1.getText().toString();
                findUserInfo(u);
            }
        });
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("扫码");
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mData.get(position).getUserName()
                        .equals(JMessageClient.getMyInfo().getUserName())) {
                    Intent i = new Intent();
                    i.setClass(context, MyInfoActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    ContactsData.addUser(mData.get(position));
                    Intent i = new Intent();
                    i.putExtra("u", mData.get(position).getUserName());
                    i.setClass(context, UserInfoActivity.class);
                    startActivity(i);
                    finish();
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void findUserInfo(String username) {
        mData.clear();
        if (JMessageClient.getMyInfo().getUserName().equals(username)) {
            mData.add(JMessageClient.getMyInfo());
            mAdapter.notifyDataSetChanged();
            is_run = false;
            return;
        }
        for (UserInfo userInfo : ContactsData.getFriendList()) {
            if (userInfo.getUserName().equals(username)) {
                mData.add(userInfo);
                mAdapter.notifyDataSetChanged();
                is_run = false;
                return;
            }
        }
        JMessageClient.getUserInfo(username, new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                if (i == 0) {
                    mData.add(userInfo);
                    mAdapter.notifyDataSetChanged();
                    is_run = false;
                } else {
                    T.l("该用户不存在");
                    mAdapter.notifyDataSetChanged();
                    is_run = false;
                }


            }
        });
    }

}
