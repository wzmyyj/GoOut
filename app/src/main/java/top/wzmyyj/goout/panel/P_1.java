package top.wzmyyj.goout.panel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.wzm_sdk.inter.IVD;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_1 extends BaseRecyclerPanel<String> {


    private View header;
    private LinearLayout ll_h_1;


    public P_1(Context context) {
        super(context);
        this.title = "推荐";
    }


    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl) {

    }

    @Override
    protected View getHeader() {
        header = mInflater.inflate(R.layout.panel_1_head, null);
        ll_h_1 = header.findViewById(R.id.ll_h_1);
        return header;
    }

    @Override
    protected View getFooter() {
        return null;
    }


    @Override
    protected List<String> getData(List<String> data) {
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        data.add("aaaaaaaaaaaa");
        return data;
    }

    @Override
    protected List<IVD<String>> getIVD(List<IVD<String>> ivd) {
        ivd.add(new IVD<String>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.panel_1_item;
            }

            @Override
            public boolean isForViewType(String item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_1, s + position);
            }
        });
        return ivd;
    }


}
