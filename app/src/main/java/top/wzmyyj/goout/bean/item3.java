package top.wzmyyj.goout.bean;

/**
 * Created by cjm on 2018/4/30.
 */

public class item3 {
    //主办方头像
    private int image1;
    //照片
    private int image2;
    //名称
    private String title;
    //地点
    private String site;
    //介绍
    private String introduce;
    //类型
    private String type;
    //点赞的数量
    private int dianZan;

    public item3(int image1, int image2, String title, String site, String introduce, int dianZan) {
        this.image1 = image1;
        this.image2 = image2;
        this.title = title;
        this.site = site;
        this.introduce = introduce;
        this.dianZan = dianZan;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getDianZan() {
        return dianZan;
    }

    public void setDianZan(int dianZan) {
        this.dianZan = dianZan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
