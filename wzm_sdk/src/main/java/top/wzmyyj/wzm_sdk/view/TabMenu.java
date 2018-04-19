package top.wzmyyj.wzm_sdk.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;


public class TabMenu extends LinearLayout {

    private int position = 0;
    private int item_count = 3;

    final static private int ITEM_COUNT_MIN = 2;
    final static private int ITEM_COUNT_MAX = 6;

    private int icon_size;
    private int text_size;
    private int item_bg;
    private int text_color1;
    private int text_color2;

    private List<LinearLayout> layouts;

    private String[] str;
    private int[] icon1;
    private int[] icon2;

    private OnMenuItemClickListener mMenuItemClickListener;

    public void setOnMenuItemClickListener(
            OnMenuItemClickListener mMenuItemClickListener) {
        this.mMenuItemClickListener = mMenuItemClickListener;
    }


    public interface OnMenuItemClickListener {
        void onClick(View view, int pos);
    }

    public TabMenu(Context context) {
        this(context, null);
    }

    public TabMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOrientation(LinearLayout.HORIZONTAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TabMenu, defStyle, 0);
        icon_size = (int) a.getDimension(R.styleable.TabMenu_icon_size, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                        getResources().getDisplayMetrics()));
        text_size = a.getDimensionPixelSize(R.styleable.TabMenu_android_textSize, 13);


        item_bg = a.getResourceId(R.styleable.TabMenu_item_bg, 0);
        text_color1 = a.getColor(R.styleable.TabMenu_text_color1, 0x777777);
        text_color2 = a.getColor(R.styleable.TabMenu_text_color2, 0x222222);
        a.recycle();


    }

    private void initView() {
        layouts = new ArrayList<>();
        for (int i = 0; i < item_count; i++) {
            final LinearLayout layout = new LinearLayout(this.getContext());
            this.addView(layout, i);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(item_bg);
            layout.setClickable(true);
            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
                    0, LayoutParams.MATCH_PARENT);
            param1.weight = 1;
            layout.setLayoutParams(param1);
            //icon
            ImageView img = new ImageView(this.getContext());
            layout.addView(img);
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                    icon_size, icon_size);
            param2.topMargin = 10;
            param2.gravity = Gravity.CENTER_HORIZONTAL;
            img.setLayoutParams(param2);
            TextView tv = new TextView(this.getContext());
            layout.addView(tv);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);

            tv.setText(str[i]);
            LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param3.gravity = Gravity.CENTER_HORIZONTAL;
            tv.setLayoutParams(param3);


            if (i == position) {
                tv.setTextColor(text_color2);
                img.setBackgroundResource(icon2[i]);
            } else {
                tv.setTextColor(text_color1);
                img.setBackgroundResource(icon1[i]);
            }
            final int pos = i;
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMenuItemClickListener != null) {
                        mMenuItemClickListener.onClick(layout, pos);
                        change(pos);
                    }
                }
            });
            layouts.add(layout);
        }
    }

    public void initItem(int item_count, int position, String[] str, int[] icon1, int[] icon2) {
        item_count = (item_count < ITEM_COUNT_MIN) ? ITEM_COUNT_MIN : item_count;
        item_count = (item_count > ITEM_COUNT_MAX) ? ITEM_COUNT_MAX : item_count;
        this.item_count = item_count;
        this.position = position;
        this.str = str;
        this.icon1 = icon1;
        this.icon2 = icon2;
        initView();
    }

    public void change(int position) {
        this.position = position;
        for (int i = 0; i < layouts.size(); i++) {
            LinearLayout layout = layouts.get(i);
            ImageView img = (ImageView) layout.getChildAt(0);
            TextView tv = (TextView) layout.getChildAt(1);
            if (i == position) {
                tv.setTextColor(text_color2);
                img.setBackgroundResource(icon2[i]);
            } else {
                tv.setTextColor(text_color1);
                img.setBackgroundResource(icon1[i]);
            }
        }
    }

}