package top.wzmyyj.goout.fragment.f_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.bean.Place;
import top.wzmyyj.goout.database.PlaceData;
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
        data.clear();
        for (Place a : PlaceData.getData()) {
            data.add(a);
        }
        return data;
    }

    @Override
    protected void update() {
        PlaceData.getRandomData();
        super.update();

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
            public void convert(ViewHolder holder, Place o, int position) {

                holder
                        .setText(R.id.tv_name, o.getName())
                        .setText(R.id.tv_title, o.getTitle())
                        .setText(R.id.tv_locale, o.getLocale())
                        .setText(R.id.tv_like, "" + o.getLike())
                ;

                ImageView img_head = holder.getView(R.id.img_head);
                ImageView img_image = holder.getView(R.id.img_image);


                Glide.with(context)
                        .load(o.getHead())
                        .into(img_head);
                Glide.with(context)
                        .load(o.getImage())
                        .into(img_image);
            }
        });
        return ivd;
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }


    private Button bt_h_1;

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_1_panel_2_header, null);
        bt_h_1 = header.findViewById(R.id.bt_h_1);
        headerListener();
        return header;
    }

    private void headerListener() {
        bt_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    @Override
    protected View getFooter() {
        return mInflater.inflate(R.layout.panel_footer, null);
    }

}
