package top.wzmyyj.goout.fragment.f_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.bean.Goods;
import top.wzmyyj.goout.database.GoodsData;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_3 extends BaseRecyclerPanel<Goods> {

    public P_3(Context context) {
        super(context);
        this.title = "商城";
    }

    @NonNull
    @Override
    protected List<Goods> getData(List<Goods> data) {
        data.clear();
        for (Goods a : GoodsData.getData()) {
            data.add(a);
        }
        return data;
    }

    @Override
    protected void update() {
        GoodsData.getRandomData();
        super.update();

    }

    @NonNull
    @Override
    protected List<IVD<Goods>> getIVD(List<IVD<Goods>> ivd) {
        ivd.add(new SingleIVD<Goods>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.goods_item;
            }

            @Override
            public void convert(ViewHolder holder, Goods o, int position) {
                holder
                        .setText(R.id.tv_name, o.getName())
                        .setText(R.id.tv_price, "￥" + o.getPrice())
                        .setText(R.id.tv_mans, o.getMans() + "人购买")
                ;

                ImageView img_image = holder.getView(R.id.img_image);

                Glide.with(context)
                        .load(o.getImage())
                        .into(img_image);
            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new GridLayoutManager(context, 3));
    }


    private TabLayout mTabLayout;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_1_panel_3_header, null);
        mTabLayout = header.findViewById(R.id.tabLayout);
        headerData();
        headerListener();
        return header;
    }

    private void headerData() {

        mTabLayout.addTab(mTabLayout.newTab().setText("综合"));
        mTabLayout.addTab(mTabLayout.newTab().setText("新品"));
        mTabLayout.addTab(mTabLayout.newTab().setText("销量"));
        mTabLayout.addTab(mTabLayout.newTab().setText("价格"));
        mTabLayout.addTab(mTabLayout.newTab().setText("分类"));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                update();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void headerListener() {

    }

    @Override
    protected View getFooter() {
        return mInflater.inflate(R.layout.panel_footer, null);
    }

}
