package top.wzmyyj.goout.application;

import top.wzmyyj.wzm_sdk.application.WzmApplication;
import top.wzmyyj.wzm_sdk.tools.L;


/**
 * Created by wzm on 2018/4/8 0008.
 */

public class MyApplication extends WzmApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        L.setTAG("GoOut");
        L.setDebug(true);
    }
}
