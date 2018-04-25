package top.wzmyyj.goout.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.LoginActivity;
import top.wzmyyj.goout.activity.UpdateInfoActivity;
import top.wzmyyj.goout.base.BaseFragment;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class F_4 extends BaseFragment {
    //top
    private ImageView img_t_1;
    private Button bt_t_1;
    private TextView tv_t_1;
    private TextView tv_t_2;
    private LinearLayout ll_a_1;
    private LinearLayout ll_a_2;
    private LinearLayout ll_a_3;
    private TextView tv_a_1;
    private TextView tv_a_2;
    private TextView tv_a_3;

    //menu
    private LinearLayout ll_m_1;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        //top
        img_t_1 = view.findViewById(R.id.img_t_1);
        tv_t_1 = view.findViewById(R.id.tv_t_1);
        tv_t_2 = view.findViewById(R.id.tv_t_2);
        bt_t_1 = view.findViewById(R.id.bt_t_1);
        ll_a_1 = view.findViewById(R.id.ll_a_1);
        ll_a_2 = view.findViewById(R.id.ll_a_2);
        ll_a_3 = view.findViewById(R.id.ll_a_3);
        tv_a_1 = view.findViewById(R.id.tv_a_1);
        tv_a_2 = view.findViewById(R.id.tv_a_2);
        tv_a_3 = view.findViewById(R.id.tv_a_3);

        //menu
        ll_m_1 = view.findViewById(R.id.ll_m_1);


        return view;
    }

    @Override
    protected void initData() {
        UserInfo myInfo = JMessageClient.getMyInfo();
        tv_t_1.setText(J.getName(myInfo));
        tv_t_2.setText(J.getSignature(myInfo));
        myInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
            @Override
            public void gotResult(int i, String s, Bitmap bitmap) {
                if (bitmap != null) {
                    img_t_1.setImageBitmap(bitmap);
                } else {
                    img_t_1.setImageResource(R.mipmap.temp);
                }
            }
        });

    }

    @Override
    protected void initListener() {
        bt_t_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity(), UpdateInfoActivity.class);
                startActivity(i);
            }
        });

        ll_m_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JMessageClient.logout();
                T.s("登出成功");
                Intent i = new Intent();
                i.setClass(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

    }
}
