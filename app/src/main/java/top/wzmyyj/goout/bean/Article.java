package top.wzmyyj.goout.bean;

/**
 * Created by cjm on 2018/4/29.
 */

public class Article {
    private int id;
    //头像
    private int head;
    //昵称
    private String name;
    //标题
    private String title;
    //文章的内容
    private String content;
    //右侧的图片
    private int image;
    //评论的数量
    private int comment;
    //喜欢的数量
    private int like;


    private int tag;

    public Article(int id, int head, String name, String title, String content, int image, int comment, int like, int tag) {
        this.id = id;
        this.head = head;
        this.name = name;
        this.title = title;
        this.content = content;
        this.image = image;
        this.comment = comment;
        this.like = like;
        this.tag = tag;
    }

    public Article(int head, String name, String title, String content, int image, int comment, int like, int tag) {
        this.head = head;
        this.name = name;
        this.title = title;
        this.content = content;
        this.image = image;
        this.comment = comment;
        this.like = like;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}

