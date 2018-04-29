package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public abstract class RecyclerPanel<T> extends InitPanel {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<T> mData;
    private List<IVD<T>> mIVD;
    protected HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    protected View mHeader;
    protected View mFooter;


    public RecyclerPanel(Context context) {
        super(context);
        this.title = "推荐";
    }

    @Override
    public void initView() {
        view = mInflater.inflate(R.layout.panel_sr, null);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(context.getResources()
                .getColor(R.color.colorBlue));

        setView(mRecyclerView, mSwipeRefreshLayout);
        mHeader = getHeader();
        mFooter = getFooter();

    }

    protected abstract void setView(RecyclerView rv, SwipeRefreshLayout srl);


    protected abstract View getHeader();

    protected abstract View getFooter();


    @Override
    public void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mData = new ArrayList<>();
        mData = getData(mData);

        mIVD = new ArrayList<>();
        mIVD = getIVD(mIVD);

        MultiItemTypeAdapter mAdapter = new MultiItemTypeAdapter(context, mData);

        for (ItemViewDelegate<T> ivd : mIVD) {
            mAdapter.addItemViewDelegate(ivd);
        }

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        if (mHeader != null)
            mHeaderAndFooterWrapper.addHeaderView(mHeader);
        if (mFooter != null)
            mHeaderAndFooterWrapper.addFootView(mFooter);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();


    }


    protected abstract List<T> getData(List<T> data);

    protected abstract List<IVD<T>> getIVD(List<IVD<T>> mIVD);

    @Override
    public void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                try {
                    upData();
                    mHeaderAndFooterWrapper.notifyDataSetChanged();
                    L.e("update data success");
                } catch (Exception e) {
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });
    }

    private void upData() {
        mData.clear();
        mData = getData(mData);
    }

}
