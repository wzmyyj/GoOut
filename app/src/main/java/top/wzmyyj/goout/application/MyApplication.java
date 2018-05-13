package top.wzmyyj.goout.application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.im.android.api.JMessageClient;
import top.wzmyyj.goout.database.NewFriend;
import top.wzmyyj.goout.utils.BitmapLoader;
import top.wzmyyj.goout.utils.gallery.GalleryUtil;
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
        GalleryUtil.init("top.wzmyyj.goout.FileProvider", "/GoOut/head");

        Configuration.Builder builder = new Configuration.Builder(this);
        //手动的添加模型类
        builder.addModelClasses(NewFriend.class);
        ActiveAndroid.initialize(builder.create());
        ZXingLibrary.initDisplayOpinion(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
