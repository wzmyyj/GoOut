package top.wzmyyj.goout.fragment.f_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.LoginActivity;
import top.wzmyyj.goout.activity.my_info.UpdateMyInfoActivity;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/30 0030.
 */

public class P_Mine extends BaseRecyclerPanel<F_4_Item> {

    public P_Mine(Context context) {
        super(context);
        this.title = "我的";
    }

    @NonNull
    @Override
    protected List<F_4_Item> getData(List<F_4_Item> data) {
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice, true));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice, true));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice));
        data.add(new F_4_Item("我的收藏", "12", R.mipmap.ic_nice, true));
        return data;
    }

    @NonNull
    @Override
    protected List<IVD<F_4_Item>> getIVD(List<IVD<F_4_Item>> ivd) {
        ivd.add(new IVD<F_4_Item>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.fragment_4_item;
            }

            @Override
            public boolean isForViewType(F_4_Item item, int position) {
                return !item.is_end();
            }

            @Override
            public void convert(ViewHolder holder, F_4_Item item, int position) {
                holder
                        .setText(R.id.tv_1, item.getWhat())
                        .setText(R.id.tv_2, item.getValue())
                        .setImageResource(R.id.img_1, item.getIcon());
            }
        });
        ivd.add(new IVD<F_4_Item>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.fragment_4_item_d;
            }

            @Override
            public boolean isForViewType(F_4_Item item, int position) {
                return item.is_end();
            }

            @Override
            public void convert(ViewHolder holder, F_4_Item item, int position) {
                holder
                        .setText(R.id.tv_1, item.getWhat())
                        .setText(R.id.tv_2, item.getValue())
                        .setImageResource(R.id.img_1, item.getIcon());
            }
        });
        return ivd;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);

        T.l("pos:" + position);
        switch (position) {
            case 1:
                JMessageClient.logout();
                Intent i = new Intent();
                i.setClass(context, LoginActivity.class);
                context.startActivity(i);
                ((Activity) context).finish();
                break;
        }
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }


    //Header
    private ImageView img_t_1;
    private Button bt_t_1;
    private TextView tv_t_1;
    private TextView tv_t_2;
    private LinearLayout ll_a_1;
    private LinearLayout ll_a_2;
    private LinearLayout ll_a_3;
    private TextView tv_a_1;
    private TextView tv_a_2;
    private TextView tv_a_3;

    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_4_top, null);

        img_t_1 = header.findViewById(R.id.img_t_1);
        tv_t_1 = header.findViewById(R.id.tv_t_1);
        tv_t_2 = header.findViewById(R.id.tv_t_2);
        bt_t_1 = header.findViewById(R.id.bt_t_1);

        ll_a_1 = header.findViewById(R.id.ll_a_1);
        ll_a_2 = header.findViewById(R.id.ll_a_2);
        ll_a_3 = header.findViewById(R.id.ll_a_3);
        tv_a_1 = header.findViewById(R.id.tv_a_1);
        tv_a_2 = header.findViewById(R.id.tv_a_2);
        tv_a_3 = header.findViewById(R.id.tv_a_3);
        headerData();
        headerListener();
        return header;
    }


    private void headerData() {
        sha = context.getSharedPreferences("log", Activity.MODE_PRIVATE);
        ed = sha.edit();
    }

    private void headerListener() {
        bt_t_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, UpdateMyInfoActivity.class);
                context.startActivity(i);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        setInfo(JMessageClient.getMyInfo());
    }

    private void setInfo(UserInfo info) {
        if (info == null) return;
        tv_t_1.setText(J.getName(info));
        tv_t_2.setText(J.getSignature(info));
        info.getAvatarBitmap(new GetAvatarBitmapCallback() {
            @Override
            public void gotResult(int i, String s, Bitmap bitmap) {
                if (bitmap != null) {
                    img_t_1.setImageBitmap(bitmap);
                    File file = JMessageClient.getMyInfo().getAvatarFile();
                    if (file != null) {
                        ed.putString("AvatarFile", file.getAbsolutePath());
                    }
                    ed.commit();
                } else {
                    img_t_1.setImageResource(R.mipmap.no_avatar);
                }
            }
        });
    }

    //Footer
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
        tv_end.setText("--end--");
    }

    private void footerListener() {

    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        setInfo(JMessageClient.getMyInfo());
    }
}
