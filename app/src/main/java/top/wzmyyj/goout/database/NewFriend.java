package top.wzmyyj.goout.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by wzm on 2018/5/3 0003.
 */

@Table(name = "nf")
public class NewFriend extends Model {
    @Column(name = "username")
    private String username;
    @Column(name = "reason")
    private String reason;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public NewFriend() {
    }

    public NewFriend(String username, String reason) {
        this.username = username;
        this.reason = reason;
    }
}

