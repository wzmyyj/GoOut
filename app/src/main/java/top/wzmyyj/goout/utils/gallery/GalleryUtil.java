package top.wzmyyj.goout.utils.gallery;

import android.app.Activity;

import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2018/4/26 0026.
 */

public class GalleryUtil {

    private static String provider;
    private static String filePath;
    private static GalleryConfig galleryConfig;

    // "包名.FileProvider"
    public static GalleryConfig init(String p, String fp) {
        provider = p;
        filePath = fp;
        return null;
    }

    public static GalleryConfig init(String p, String fp, IHandlerCallBack iHandlerCallBack) {
        provider = p;
        filePath = fp;
        return initGallery(iHandlerCallBack);
    }


    public static GalleryConfig initGallery(IHandlerCallBack iHandlerCallBack) {
        if (provider == null) {
            return null;
        }
        if (iHandlerCallBack == null) {
            return null;
        }

        if (filePath == null) {
            filePath = "/GalleryPick/Image";
        }

        List<String> path = new ArrayList<>();
        //init galleryConfig
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider(provider)   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 3)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(true, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath(filePath)          // 图片存放路径
                .isOpenCamera(false)                    // 是否直接打开相机
                .build();
        return galleryConfig;
    }


    public static GalleryConfig getGallery() {
        return galleryConfig;
    }

    public static void open(Activity activity) {
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(activity);
    }
}
