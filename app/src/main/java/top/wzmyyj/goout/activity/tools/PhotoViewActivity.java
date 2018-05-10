package top.wzmyyj.goout.activity.tools;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.adapter.ImageViewPagerAdapter;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.wzm_sdk.tools.T;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ImageView img_1;
    private ViewPager mViewPager;

    private ArrayList<ImageView> mImageList;
    private List<String> mPath;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_photo_view);
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewPager);
        img_1 = findViewById(R.id.img_1);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String s = intent.getStringExtra("path");
        if (TextUtils.isEmpty(s)) {
            return;
        }
        mImageList = new ArrayList<>();
        mPath = new ArrayList<>();
        mPath.add(s);

        try {
            for (int i = 0; i < mPath.size(); i++) {
                PhotoView photoView = new PhotoView(context);
                Glide.with(activity)
                        .load(s)
                        .placeholder(R.mipmap.gallery_pick_photo)
                        .into(photoView);

                PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);
                attacher.update();
                mImageList.add(photoView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(mImageList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(mViewPager.getCurrentItem());
            }
        });

    }

    /*gg*/
    private void saveImage(int i) {
        T.s("保存成功");
    }
}
