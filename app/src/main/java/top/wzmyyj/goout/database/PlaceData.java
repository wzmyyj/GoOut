package top.wzmyyj.goout.database;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.data.Data_Place;
import top.wzmyyj.goout.bean.Place;
import top.wzmyyj.goout.tools.RandomSort;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class PlaceData extends Data_Place {


    private static List<Place> mData;

    public static List<Place> getData() {
        if (mData != null) {
            return mData;
        }
        return getRandomData();
    }

    public static List<Place> getRandomData() {
        int[] a = RandomSort.getInt(head.length);
        List<Place> data = new ArrayList<>();
        for (int j = 0; j < head.length; j++) {
            int i = a[j];
            data.add(new Place(head[i], name[i],
                    title[i], locale[i], image[i],
                    like[i], tag[i]));
        }
        mData = data;
        return mData;
    }


}
