package top.wzmyyj.goout.database;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.data.Data_Article;
import top.wzmyyj.goout.bean.Article;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class ArticleData extends Data_Article {


    public static List<Article> getData() {
        List<Article> mData = new ArrayList<>();
        for (int i = 0; i < head.length; i++) {
            mData.add(new Article(head[i], name[i],
                    title[i], content[i], image[i],
                    comment[i], like[i], tag[i]));
        }
        return mData;
    }
}
