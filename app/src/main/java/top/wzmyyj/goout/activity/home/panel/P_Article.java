package top.wzmyyj.goout.activity.home.panel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseNestedScrollPanel;
import top.wzmyyj.goout.bean.Article;
import top.wzmyyj.goout.database.ArticleData;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public class P_Article extends BaseNestedScrollPanel {
    public P_Article(Context context) {
        super(context);
        this.title = "文章";
    }

    private int aid = -1;
    private Article article;

    @Override
    public void initSome(Bundle savedInstanceState) {
        Intent i = activity.getIntent();
        aid = i.getIntExtra("aid", -1);
        if (aid == -1) {
            activity.finish();
        }
        article= ArticleData.getArticle(aid);
        if(article==null){
            activity.finish();
        }
        super.initSome(savedInstanceState);
    }

    @Override
    protected void setView(NestedScrollView ns, SwipeRefreshLayout srl, FrameLayout layout) {

    }

    @Override
    protected View getContentView() {
        View content = mInflater.inflate(R.layout.activity_article, null);
        return content;
    }
}
