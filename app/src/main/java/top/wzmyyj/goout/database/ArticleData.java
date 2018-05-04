package top.wzmyyj.goout.database;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.data.Data_Article;
import top.wzmyyj.goout.bean.Article;
import top.wzmyyj.goout.tools.RandomSort;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class ArticleData extends Data_Article {


    private static List<Article> mData;

    public static List<Article> getData() {
        if (mData != null) {
            return mData;
        }
        return getRandomData();
    }

    public static List<Article> getRandomData() {
        int[] a = RandomSort.getInt(head.length);
        List<Article> data = new ArrayList<>();
        for (int j = 0; j < head.length; j++) {
            int i = a[j];
            data.add(new Article(head[i], name[i],
                    title[i], content[i], image[i],
                    comment[i], like[i], tag[i]));
        }
        mData = data;
        return mData;
    }


}
