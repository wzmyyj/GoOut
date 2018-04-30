package top.wzmyyj.goout.fragment.f_4;

/**
 * Created by wzm on 2018/4/30 0030.
 */

public class F_4_Item {
    private String what;
    private String value;
    private int icon;
    private boolean is_end;

    public F_4_Item(String what, String value, int icon) {
        this(what, value, icon, false);
    }


    public F_4_Item(String what, String value, int icon, boolean is_end) {
        this.what = what;
        this.value = value;
        this.icon = icon;
        this.is_end = is_end;

    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean is_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }
}
