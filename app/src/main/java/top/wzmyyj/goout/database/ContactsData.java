package top.wzmyyj.goout.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetGroupIDListCallback;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.tools.FriendComparator;
import top.wzmyyj.goout.tools.GroupComparator;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class ContactsData {

    private static List<UserInfo> friendList = new ArrayList<>();
    private static List<GroupInfo> groupList = new ArrayList<>();


    private static List<UserInfo> userList = new ArrayList<>();

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

    public static void addUser(UserInfo userInfo) {
        if (!userList.contains(userInfo))
            userList.add(userInfo);
    }

    public static UserInfo getUser(String username) {

        for (UserInfo userInfo : friendList) {
            if (userInfo.getUserName().equals(username)) {
                return userInfo;
            }
        }
        for (UserInfo userInfo : userList) {
            if (userInfo.getUserName().equals(username)) {
                return userInfo;
            }
        }
        return null;
    }

    public static GroupInfo getGroup(long ID) {
        for (GroupInfo groupInfo : groupList) {
            if (groupInfo.getGroupID() == ID) {
                return groupInfo;
            }
        }
        return null;
    }

    public static void delUser(String username) {
        for (UserInfo userInfo : userList) {
            if (userInfo.getUserName().equals(username)) {
                userList.remove(userInfo);
            }
        }
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
        UserInfo user = null;
        for (UserInfo userInfo : friendList) {
            if (userInfo.getUserName().equals(s)) {
                user = userInfo;
                break;
            }
        }
        friendList.remove(user);
    }

    public static void delGroup(long l) {
        GroupInfo group = null;
        for (GroupInfo groupInfo : groupList) {
            if (groupInfo.getGroupID() == l) {
                group = groupInfo;
                break;
            }
        }
        groupList.remove(group);
    }

    public static void addFriend(UserInfo userInfo) {
        friendList.add(userInfo);
    }


    public static void initFriendList() {
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (i == 0) {
                    friendList.clear();
                    for (UserInfo info : list) {
                        friendList.add(info);
                    }
                    Comparator comp = new FriendComparator();
                    Collections.sort(friendList, comp);
                }
            }
        });
    }

    public static void initGroupList() {
        JMessageClient.getGroupIDList(new GetGroupIDListCallback() {
            @Override
            public void gotResult(int i, String s, List<Long> list) {
                if (i == 0) {
                    if (list == null) return;
                    final int h = list.size();
                    groupList.clear();
                    for (Long l : list) {
                        JMessageClient.getGroupInfo(l,
                                new GetGroupInfoCallback() {

                                    @Override
                                    public void gotResult(int i,
                                                          String s, GroupInfo groupInfo) {
                                        groupList.add(groupInfo);
                                        if (groupList.size() == h) {
                                            Comparator comp = new GroupComparator();
                                            Collections.sort(groupList, comp);
                                        }
                                    }
                                });

                    }
                }
            }
        });
    }

//    public static void addGroup(long l) {
//        JMessageClient.getGroupInfo(l,
//                new GetGroupInfoCallback() {
//
//                    @Override
//                    public void gotResult(int i,
//                                          String s, GroupInfo groupInfo) {
//                        groupList.add(groupInfo);
//                        Comparator comp = new GroupComparator();
//                        Collections.sort(groupList, comp);
//                    }
//                });
//
//    }

    public static void addGroup(GroupInfo group) {
        groupList.add(group);
        Comparator comp = new GroupComparator();
        Collections.sort(groupList, comp);
    }

    public static void updateGroup(GroupInfo group) {
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getGroupID() == group.getGroupID()) {
                groupList.set(i, group);
                break;
            }
        }
        if (!groupList.contains(group)) {
            addGroup(group);
        }
    }

}
