package top.wzmyyj.goout.panel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BasePanel;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_1 extends BasePanel {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mData;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    private View view1;
    private LinearLayout ll_h_1;


    public P_1(Context context) {
        super(context);
        this.title = "推荐";
    }

    @Override
    public void initView() {
        view = mInflater.inflate(R.layout.panel_1, null);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        view1 = mInflater.inflate(R.layout.panel_1_head, null);
        ll_h_1 = view1.findViewById(R.id.ll_h_1);

    }

    @Override
    public void initData() {

        mSwipeRefreshLayout.setColorSchemeColors(mInflater
                .getContext()
                .getResources()
                .getColor(R.color.colorBlue));

        getData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mInflater.getContext()));

        CommonAdapter<String> mAdapter = new CommonAdapter<String>
                (mInflater.getContext(), R.layout.panel_1_item, mData) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

                holder.setText(R.id.tv_1, s);
            }
        };

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(view1);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();


    }

    @Override
    public void initListener() {
        ll_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.set(1, "ggggggggggggggg");
                mHeaderAndFooterWrapper.notifyDataSetChanged();

            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                try {
                    getData();
                    mHeaderAndFooterWrapper.notifyDataSetChanged();
                    L.e("update data success");
                    Thread.sleep(500);
                    mSwipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }


            }
        });
    }

    protected void getData() {
        mData = new ArrayList<>();

        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
        mData.add("aaaaaaaaaaaa");
    }
}
