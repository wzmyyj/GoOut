package top.wzmyyj.wzm_sdk.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.view.TabMenu;

/**
 * Created by wzm on 2018/4/18 0018.
 */

public abstract class FPT_Activity extends WzmActivity {
    private List<Fragment> mFragmentList;
    private TabMenu mTabMenu;
    private ViewPager mViewPager;

    protected class FPT {
        Fragment fragment;
        String string;
        int icon1;
        int icon2;

        public FPT(Fragment fragment, String string, int icon1, int icon2) {
            this.fragment = fragment;
            this.string = string;
            this.icon1 = icon1;
            this.icon2 = icon2;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.fp_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabMenu = (TabMenu) findViewById(R.id.tabMenu);

    }

    @Override
    protected void initData() {
        List<FPT> mData = getFPT();
        int position = getPosition();

        if (mData == null) return;

        mFragmentList = new ArrayList<>();
        int c = mData.size();
        String[] s = new String[c];
        int[] i1 = new int[c];
        int[] i2 = new int[c];

        for (int i = 0; i < c; i++) {
            mFragmentList.add(mData.get(i).fragment);
            s[i] = mData.get(i).string;
            i1[i] = mData.get(i).icon1;
            i2[i] = mData.get(i).icon2;
        }
        mTabMenu.initItem(c, position, s, i1, i2);

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int a) {
                return mFragmentList.get(a);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }
        };
        mViewPager.setAdapter(mAdapter);

    }

    protected abstract List<FPT> getFPT();

    protected int getPosition() {
        return 0;
    }


    @Override
    protected void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabMenu.change(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabMenu.setOnMenuItemClickListener(new TabMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                mViewPager.setCurrentItem(pos);
            }
        });
    }


}
