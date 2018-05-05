package top.wzmyyj.goout.database;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.data.Data_Store;
import top.wzmyyj.goout.bean.Goods;
import top.wzmyyj.goout.tools.RandomSort;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class GoodsData extends Data_Store {


    private static List<Goods> mData;

    public static List<Goods> getData() {
        if (mData != null) {
            return mData;
        }
        return getRandomData();
    }

    public static List<Goods> getRandomData() {
        int[] a = RandomSort.getInt(image.length);
        List<Goods> data = new ArrayList<>();
        for (int j = 0; j < image.length; j++) {
            int i = a[j];
            data.add(new Goods(image[i], name[i],
                    price[i], mans[i]));
        }
        mData = data;
        return mData;
    }


}
