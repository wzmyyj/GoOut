package top.wzmyyj.goout.bean;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class Goods {

    private int image;
    private String name;
    private double price;
    private int mans;

    public Goods() {
    }

    public Goods(int image, String name, double price, int mans) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.mans = mans;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMans() {
        return mans;
    }

    public void setMans(int mans) {
        this.mans = mans;
    }
}
