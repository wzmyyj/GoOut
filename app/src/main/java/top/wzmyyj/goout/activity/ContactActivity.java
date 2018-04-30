package top.wzmyyj.goout.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseMultiPanelActivity;
import top.wzmyyj.goout.panel.P_FriendList;
import top.wzmyyj.goout.panel.P_GroupList;
import top.wzmyyj.wzm_sdk.adapter.CommonAdapter;
import top.wzmyyj.wzm_sdk.panel.InitPanel;

public class ContactActivity extends BaseMultiPanelActivity {

    private QMUIListPopup mListPopup;


    @Override
    protected List<InitPanel> getPanelList(List<InitPanel> mPanelList) {
        mPanelList.add(new P_FriendList(this));
        mPanelList.add(new P_GroupList(this));
        return mPanelList;
    }

    @Override
    protected void setView(Toolbar toolbar, TabLayout tab, ViewPager vp, ImageView img) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img.setImageResource(R.drawable.ic_add);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
                mListPopup.show(v);
            }
        });
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {
            String[] listItems = new String[]{
                    "发起单聊",
                    "发起群聊",
                    "添加好友",
                    "扫一扫"
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            mListPopup = new QMUIListPopup(context, QMUIPopup.DIRECTION_NONE,
                    new CommonAdapter<String>(context, data, R.layout.popup_item) {
                        @Override
                        public void convert(ViewHolder holder, String bean, int position) {
                            holder.setText(R.id.tv_1, bean);
                        }
                    });

            mListPopup.create(QMUIDisplayHelper.dp2px(context, 110), QMUIDisplayHelper.dp2px(context, 180),
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mListPopup.dismiss();
                            Intent intent = new Intent();
                            switch (i) {
                                case 0:
                                    intent.setClass(context, AddFriendActivity.class);
                                    break;
                                case 1:
                                    intent.setClass(context, AddFriendActivity.class);
                                    break;
                                case 2:
                                    intent.setClass(context, AddFriendActivity.class);
                                    break;
                                case 3:
                                    intent.setClass(context, AddFriendActivity.class);
                                    break;
                            }
                            startActivity(intent);
                        }
                    });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
        }
    }

}
