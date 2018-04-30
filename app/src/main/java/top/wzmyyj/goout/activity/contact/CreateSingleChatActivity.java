package top.wzmyyj.goout.activity.contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.data.ContactsData;
import top.wzmyyj.goout.tools.J;

public class CreateSingleChatActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ImageView img_1;
    private RecyclerView mRecyclerView;
    private List<UserInfo> mData;
    private CommonAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_create_single_chat);
        mToolbar = findViewById(R.id.toolbar);
        img_1 = findViewById(R.id.img_1);
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
                Intent i=new Intent();
                i.setClass(context,FindFriendActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent i = new Intent();
                i.putExtra("u", mData.get(position).getUserName());
                i.setClass(context, SingleChatActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


}
