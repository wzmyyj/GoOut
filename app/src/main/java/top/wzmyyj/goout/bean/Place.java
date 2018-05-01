package top.wzmyyj.goout.bean;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class Place {

    private String name;

    private int head;

    private String title;

    private String locale;

    private int image;

    private int like;

    private int tag;

    public Place() {
    }

    public Place(String name, int head, String title, String locale, int image, int like, int tag) {
        this.name = name;
        this.head = head;
        this.title = title;
        this.locale = locale;
        this.image = image;
        this.like = like;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
