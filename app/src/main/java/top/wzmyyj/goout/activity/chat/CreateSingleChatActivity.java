package top.wzmyyj.goout.activity.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.contact.FindFriendActivity;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.tools.T;

public class CreateSingleChatActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ImageView img_1;
    private RecyclerView mRecyclerView;
    private List<UserInfo> mData;
    private CommonAdapter mAdapter;
    private EditText et_1;
    private Button bt_1;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_create_single_chat);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
        et_1 = findViewById(R.id.et_1);
        bt_1 = findViewById(R.id.bt_1);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mData = ContactsData.getFriendList();
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

            }
        };

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


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
                Intent i = new Intent();
                i.setClass(context, FindFriendActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent i = new Intent();
                UserInfo user=mData.get(position);
                i.putExtra("u", user.getUserName());
                i.putExtra("n", J.getName(user));
                i.setClass(context, SingleChatActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
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
    }


    private boolean is_run = false;

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
