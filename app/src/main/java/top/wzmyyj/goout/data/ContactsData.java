package top.wzmyyj.goout.data;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class ContactsData {

    private static List<UserInfo> friendList = new ArrayList<>();
    private static List<GroupInfo> groupList = new ArrayList<>();


    private ContactsData() {

    }

    public static ContactsData getInstance() {
        return Data_Contacts_Holder.instance;
    }

    private static class Data_Contacts_Holder {
        private static ContactsData instance = new ContactsData();
    }

    public static void setFriendList(List<UserInfo> friendList) {
        ContactsData.friendList = friendList;
    }

    public static List<UserInfo> getFriendList() {
        return friendList;
    }

    public static List<GroupInfo> getGroupList() {
        return groupList;
    }


    public static void setGroupList(List<GroupInfo> groupList) {
        ContactsData.groupList = groupList;
    }

    public static void delFriend(String s) {
        for (UserInfo userInfo : friendList) {
            if (userInfo.getUserName().equals(s)) {
                friendList.remove(userInfo);
            }
        }
    }

    public static void delGroup(long l) {
        for (GroupInfo groupInfo : groupList) {
            if (groupInfo.getGroupID() == l) {
                groupList.remove(groupInfo);
            }
        }
    }

    public static void addFriend(UserInfo userInfo) {
        friendList.add(userInfo);
    }

    public static void addGroup(GroupInfo groupInfo) {
        groupList.add(groupInfo);
    }


}
