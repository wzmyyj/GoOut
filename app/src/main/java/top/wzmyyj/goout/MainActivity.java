package top.wzmyyj.goout;


import android.content.Intent;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.LoginStateChangeEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import top.wzmyyj.goout.activity.chat.GroupChatActivity;
import top.wzmyyj.goout.activity.chat.SingleChatActivity;
import top.wzmyyj.goout.activity.contact.NewFriendActivity;
import top.wzmyyj.goout.base.BaseMainActivity;
import top.wzmyyj.goout.database.NewFriend;
import top.wzmyyj.goout.fragment.f_1.F_1;
import top.wzmyyj.goout.fragment.f_2.F_2;
import top.wzmyyj.goout.fragment.f_3.F_3;
import top.wzmyyj.goout.fragment.f_4.F_4;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.wzm_sdk.tools.T;

public class MainActivity extends BaseMainActivity {


    @Override
    protected FPT getFPT(FPT fpt) {
        fpt
                .add(new F_1(), "主页", R.mipmap.ic_home_0, R.mipmap.ic_home_1)
                .add(new F_2(), "活动", R.mipmap.ic_timeline_0, R.mipmap.ic_timeline_1)
                .add(new F_3(), "消息", R.mipmap.ic_message_0, R.mipmap.ic_message_1)
                .add(new F_4(), "我的", R.mipmap.ic_mine_0, R.mipmap.ic_mine_1);
        return fpt;
    }


    public void onEvent(NotificationClickEvent event) {

        Message msg = event.getMessage();
        Intent i = new Intent();
        switch (msg.getTargetType()) {
            case single:
                UserInfo userInfo = (UserInfo) msg.getTargetInfo();
                i.putExtra("u", userInfo.getUserName());
                i.putExtra("n", J.getName(userInfo));
                i.setClass(getApplicationContext(), SingleChatActivity.class);
                startActivity(i);
                break;
            case group:
                GroupInfo groupInfo = (GroupInfo) msg.getTargetInfo();
                i.putExtra("id", groupInfo.getGroupID());
                i.putExtra("n", groupInfo.getGroupName());
                i.setClass(getApplicationContext(), GroupChatActivity.class);
                startActivity(i);
                break;
        }

    }

    public void onEvent(ContactNotifyEvent event) {
        String reason = event.getReason();
        String fromUsername = event.getFromUsername();
        L.d("onEvent: " + event.getType());

        switch (event.getType()) {
            case invite_received:// 收到好友邀请
                showMessageDialog(1, "收到" + fromUsername
                        + "好友邀请"
                        + "\n"
                        + "原因："
                        + reason);

                NewFriend nf = new NewFriend(fromUsername, reason);
                nf.save();
                break;
            case invite_accepted:// 对方接收了你的好友邀请
                showMessageDialog(fromUsername + "接收了你的好友邀请");
                break;
            case invite_declined:// 对方拒绝了你的好友邀请
                showMessageDialog(fromUsername
                        + "拒绝了你的好友邀请"
                        + "\n"
                        + "原因："
                        + reason);
                break;
            case contact_deleted:// 对方将你从好友中删除
                showMessageDialog(fromUsername + "将你从好友中删除");
                break;
            default:
                break;
        }
    }

    public void onEvent(LoginStateChangeEvent event) {
        LoginStateChangeEvent.Reason reason = event.getReason();
        UserInfo myInfo = event.getMyInfo();
        T.l("reason = " + reason + "\n" + "logout user name = " + myInfo.getUserName());
        finish();
    }


    private void showMessageDialog(String msg) {
        showMessageDialog(0, msg);
    }

    private void showMessageDialog(final int w, String msg) {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage(msg)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        if (w == 1) {
                            Intent i = new Intent();
                            i.setClass(context, NewFriendActivity.class);
                            startActivity(i);
                        }
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
