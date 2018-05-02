package top.wzmyyj.goout.activity.contact.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.FriendComparator;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_FriendList extends BaseRecyclerPanel<UserInfo> {


    public P_FriendList(Context context) {
        super(context);
        this.title = "好友";
    }

    @NonNull
    @Override
    protected List<UserInfo> getData(List<UserInfo> data) {
        getFriendList();
        return data;
    }

    @Override
    protected void update() {
        getFriendList();
    }


    private void getFriendList() {
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (i == 0) {
                    mData.clear();
                    for (UserInfo info : list) {
                        mData.add(info);
                    }
                    Comparator comp = new FriendComparator();
                    Collections.sort(mData, comp);
                    notifyDataSetChanged();
                    ContactsData.setFriendList(mData);
                }
            }
        });
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
            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }

    @Override
    protected View getHeader() {
        headerData();
        headerListener();
        return null;
    }

    private void headerData() {

    }

    private void headerListener() {
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
            tv_end.setText("--没有好友TAT--");
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
