package top.wzmyyj.goout.activity.contact.panel;

import android.content.Context;
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

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetGroupIDListCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.database.ContactsData;
import top.wzmyyj.goout.tools.GroupComparator;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_GroupList extends BaseRecyclerPanel<GroupInfo> {


    public P_GroupList(Context context) {
        super(context);
        this.title = "群组";
    }

    @NonNull
    @Override
    protected List<GroupInfo> getData(List<GroupInfo> data) {
        getGroupList();
        return data;
    }

    @Override
    protected void update() {
        getGroupList();
    }


    private void getGroupList() {
        JMessageClient.getGroupIDList(new GetGroupIDListCallback() {
            @Override
            public void gotResult(int i, String s, List<Long> list) {
                if (i == 0) {
                    if (list == null) return;
                    final int h = list.size();
                    mData.clear();
                    for (Long l : list) {
                        JMessageClient.getGroupInfo(l,
                                new GetGroupInfoCallback() {

                                    @Override
                                    public void gotResult(int i,
                                                          String s, GroupInfo groupInfo) {
                                        mData.add(groupInfo);
                                        if (mData.size() == h) {
                                            Comparator comp = new GroupComparator();
                                            Collections.sort(mData, comp);
                                            notifyDataSetChanged();
                                            ContactsData.setGroupList(mData);
                                        }
                                    }
                                });

                    }
                }
            }
        });
    }

    @NonNull
    @Override
    protected List<IVD<GroupInfo>> getIVD(List<IVD<GroupInfo>> ivd) {
        ivd.add(new SingleIVD<GroupInfo>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.friend_list_item;
            }

            @Override
            public void convert(ViewHolder holder, GroupInfo groupInfo, int position) {
                final ImageView img = holder.getView(R.id.img_1);
                TextView tv = holder.getView(R.id.tv_1);
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
            tv_end.setText("--没有群组TAT--");
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
