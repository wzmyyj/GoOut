package top.wzmyyj.goout.fragment.f_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.bean.Goods;
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
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        data.add(new Goods());
        return data;
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
            public void convert(ViewHolder holder, Goods goods, int position) {

            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    protected View getHeader() {
        return null;
    }

    @Override
    protected View getFooter() {
        return mInflater.inflate(R.layout.panel_footer, null);
    }

}
