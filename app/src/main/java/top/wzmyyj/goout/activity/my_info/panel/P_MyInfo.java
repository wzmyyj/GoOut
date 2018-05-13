package top.wzmyyj.goout.activity.my_info.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseNestedScrollPanel;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.java.TimeUtil;

/**
 * Created by wzm on 2018/5/10 0010.
 */

public class P_MyInfo extends BaseNestedScrollPanel {

    public P_MyInfo(Context context) {
        super(context);
        this.title = "个人资料";
    }

    @Override
    protected void setView(NestedScrollView ns, SwipeRefreshLayout srl, FrameLayout layout) {

    }


    private ImageView img_m_1;
    private TextView tv_m_1;
    private TextView tv_m_2;
    private TextView tv_m_3;
    private TextView tv_m_4;
    private TextView tv_m_5;
    private TextView tv_m_6;
    private ImageView img_zxing;

    @NonNull
    @Override
    protected View getContentView() {
        View content = mInflater.inflate(R.layout.activity_my_info, null);
        img_m_1 = content.findViewById(R.id.img_m_1);
        tv_m_1 = content.findViewById(R.id.tv_m_1);
        tv_m_2 = content.findViewById(R.id.tv_m_2);
        tv_m_3 = content.findViewById(R.id.tv_m_3);
        tv_m_4 = content.findViewById(R.id.tv_m_4);
        tv_m_5 = content.findViewById(R.id.tv_m_5);
        tv_m_6 = content.findViewById(R.id.tv_m_6);
        img_zxing = content.findViewById(R.id.img_zxing);
        contentData();
        contentListener();
        return content;
    }

    @Override
    protected void changeView() {
        super.changeView();
        setInfo(JMessageClient.getMyInfo());
    }

    private void contentData() {
        setInfo(JMessageClient.getMyInfo());
    }

    private void setInfo(UserInfo info) {
        if (info == null) return;
        tv_m_1.setText(info.getUserName());
        tv_m_2.setText(info.getNickname());
        tv_m_3.setText(info.getSignature());
        tv_m_4.setText(J.getGender(info));
        tv_m_5.setText(TimeUtil.changeToString(info.getBirthday(), "yyyy-MM-dd"));
        tv_m_6.setText(info.getRegion());

        final String textContent = "{user='" + info.getUserName() + "'}";
        info.getAvatarBitmap(new GetAvatarBitmapCallback() {
            @Override
            public void gotResult(int i, String s, Bitmap bitmap) {
                if (bitmap != null) {
                    img_m_1.setImageBitmap(bitmap);
                    Bitmap image = CodeUtils.createImage(textContent, 400, 400, bitmap);
                    img_zxing.setImageBitmap(image);
                } else {
                    img_m_1.setImageResource(R.mipmap.no_avatar);
                    Bitmap image = CodeUtils.createImage(textContent, 400, 400,
                            BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                    img_zxing.setImageBitmap(image);
                }
            }
        });

    }

    private void contentListener() {
    }


}
