package top.wzmyyj.goout.panel;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.adapter.MyBoAdapter;
import top.wzmyyj.goout.base.BaseRecyclerPanel;
import top.wzmyyj.goout.data.BoData;
import top.wzmyyj.goout.tools.FixedSpeedScroller;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class P_1 extends BaseRecyclerPanel<String> {


    private ViewPager mVp_bo;
    private LinearLayout ll_h_1;
    private LinearLayout ll_h_2;
    private LinearLayout ll_h_3;
    private LinearLayout ll_h_4;

    private ArrayList<ImageView> mImageList;
    private int[] imageIds = BoData.imgList;

    //bo
    private Handler mHandler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            if (mVp_bo != null) {
                mVp_bo.setCurrentItem(mVp_bo.getCurrentItem() + 1);
                mHandler.postDelayed(myRunnable, 4000);
            }
        }
    }


    public P_1(Context context) {
        super(context);
        this.title = "推荐";
    }

    @NonNull
    @Override
    protected List<String> getData(List<String> data) {
        data.clear();
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

    @NonNull
    @Override
    protected List<IVD<String>> getIVD(List<IVD<String>> ivd) {
        ivd.add(new IVD<String>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.fragment_1_panel_1_item;
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


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        T.s(mData.get(position)  + position);
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }

    @Override
    protected View getHeader() {
        View header = mInflater.inflate(R.layout.fragment_1_panel_1_head, null);
        mVp_bo = header.findViewById(R.id.viewPager);
        ll_h_1 = header.findViewById(R.id.ll_h_1);
        ll_h_2 = header.findViewById(R.id.ll_h_2);
        ll_h_3 = header.findViewById(R.id.ll_h_3);
        ll_h_4 = header.findViewById(R.id.ll_h_4);
        headerData();
        headerListener();
        return header;
    }

    private void headerData() {
        initBo();
    }

    private void headerListener() {

    }

    private void initBo() {
        mImageList = new ArrayList<ImageView>();
        try {
            for (int i = 0; i < imageIds.length; i++) {
                ImageView image = new ImageView(context);
                image.setBackgroundResource(imageIds[i]);
                mImageList.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mVp_bo.setAdapter(new MyBoAdapter(mImageList));

        //利用反射修改ViewPager切换速度
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller =
                    new FixedSpeedScroller(mVp_bo.getContext(), 2 * 1000);
            mField.set(mVp_bo, mScroller);
        } catch (Exception e) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.removeCallbacks(myRunnable);
        mHandler.postDelayed(myRunnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(myRunnable);
    }

    @Override
    protected View getFooter() {
        return mInflater.inflate(R.layout.panel_footer, null);
    }


}
