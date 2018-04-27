package top.wzmyyj.goout.application;

import cn.jpush.im.android.api.JMessageClient;
import top.wzmyyj.goout.utils.BitmapLoader;
import top.wzmyyj.wzm_sdk.application.WZM_Application;
import top.wzmyyj.wzm_sdk.tools.L;


/**
 * Created by wzm on 2018/4/8 0008.
 */

public class MyApplication extends WZM_Application {

    @Override
    public void onCreate() {
        super.onCreate();
        L.setTAG("GoOut");
        L.setDebug(true);
        JMessageClient.init(this);
        BitmapLoader.init("sdcard/GoOut/pictures/");
    }
}
