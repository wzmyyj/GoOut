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
import top.wzmyyj.goout.bean.Place;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_2 extends BaseRecyclerPanel<Place> {

    public P_2(Context context) {
        super(context);
        this.title = "周边";
    }

    @NonNull
    @Override
    protected List<Place> getData(List<Place> data) {
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        data.add(new Place());
        return data;
    }


    @NonNull
    @Override
    protected List<IVD<Place>> getIVD(List<IVD<Place>> ivd) {
        ivd.add(new SingleIVD<Place>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.place_item;
            }

            @Override
            public void convert(ViewHolder holder, Place place, int position) {

//                ll_p.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        T.s("hhh");
//                    }
//                });
            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new GridLayoutManager(context, 2));

    }

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_1_panel_2_header, null);
        return header;
    }

    @Override
    protected View getFooter() {
        return mInflater.inflate(R.layout.panel_footer, null);
    }

}
