package top.wzmyyj.goout.activity.chat;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import sj.qqkeyboard.DefQqEmoticons;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.activity.contact.UserInfoActivity;
import top.wzmyyj.goout.activity.my_info.MyInfoActivity;
import top.wzmyyj.goout.adapter.MessageAdapter;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.utils.gallery.GalleryUtil;
import top.wzmyyj.wzm_sdk.tools.T;


public class GroupChatActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ListView mList;
    private MessageAdapter mAdapter;
    private List<Message> mData;
    private EditText mEt_input;
    private Button mBt_send;
    private long ID;

    //exp
    private RelativeLayout mRL_1;
    private LinearLayout ll_exp;
    private GridView gv_exp;
    private List<String> key_exp;
    private List<Map<String, Object>> list_exp;
    private SimpleAdapter simple_exp;

    //image
    private RelativeLayout mRL_3;


    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.chat_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mList = (ListView) findViewById(R.id.lv_chat);
        mEt_input = (EditText) findViewById(R.id.et_chat_input);
        mBt_send = (Button) findViewById(R.id.bt_chat_send);

        //exp
        mRL_1 = (RelativeLayout) findViewById(R.id.rl_chat_1);
        ll_exp = (LinearLayout) findViewById(R.id.ll_chat_exp);
        gv_exp = (GridView) findViewById(R.id.gv_chat_exp);


        //image
        mRL_3 = (RelativeLayout) findViewById(R.id.rl_chat_3);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        Intent i = getIntent();
        ID = i.getLongExtra("id", 0);
        String n = i.getStringExtra("n");
        mToolbar.setTitle(n);
        mData = new ArrayList<>();
        mAdapter = new MessageAdapter(this, mData, mListener);
        mList.setAdapter(mAdapter);
        getHistory();
        JMessageClient.enterGroupConversation(ID);
        initExp();
    }

    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_exp.setVisibility(View.GONE);
                final String text = mEt_input.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    Message message = JMessageClient.createGroupTextMessage(ID, text);
                    message.setOnSendCompleteCallback(new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i == 0) {
                                mAdapter.notifyDataSetChanged();
                            } else {
                                T.s("发送失败");
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    JMessageClient.sendMessage(message);
                    mData.add(message);
                    mAdapter.notifyDataSetChanged();
                    mEt_input.setText("");
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            mList.setSelection(mAdapter.getCount());
                        }
                    }, 100);


                }
            }
        });

        mEt_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        ll_exp.setVisibility(View.GONE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                mList.setSelection(mAdapter.getCount());
                            }
                        }, 100);
                        break;
                }
                return false;
            }
        });

        //exp
        mRL_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ll_exp.getVisibility() == View.GONE) {
                    ll_exp.setVisibility(View.VISIBLE);
                } else {
                    ll_exp.setVisibility(View.GONE);
                }
            }
        });
        gv_exp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpannableString ss = new SpannableString(key_exp.get(i));
                Drawable drawable = getResources().getDrawable((Integer) list_exp.get(i).get("pic"));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ss.setSpan(new ImageSpan(drawable), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                mEt_input.append(ss);
            }
        });


        //image
        mRL_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }


    private void seeGroupInfo() {
//        Intent i = new Intent();
//        i.putExtra("id", ID);
//        i.setClass(GroupChat.this, .class);
//        startActivity(i);
    }

    public void onEvent(MessageEvent event) {
        Message message = event.getMessage();
        if (message.getTargetType() != ConversationType.group) {
            return;
        }

        GroupInfo groupInfo = (GroupInfo) message.getTargetInfo();
        if (groupInfo.getGroupID() != ID) {
            return;
        }
        mData.add(message);
        mAdapter = new MessageAdapter(this, mData, mListener);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mList.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mList.setSelection(mAdapter.getCount());
            }
        });
    }


    private void getHistory() {
        Conversation conversation = JMessageClient.getGroupConversation(ID);
        if (conversation == null) {
            return;
        }
        List<Message> messageList = conversation.getMessagesFromOldest(
                conversation.getLatestMessage().getId() - 50, 50);

        for (Message message : messageList) {
            mData.add(message);
            mAdapter.notifyDataSetChanged();
            mList.setSelection(mAdapter.getCount());

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Conversation conversation = JMessageClient.getGroupConversation(ID);
        if (conversation != null) {
            conversation.resetUnreadCount();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.exitConversation();
        JMessageClient.unRegisterEventReceiver(this);
        finish();
    }

    private MessageAdapter.MyClickListener mListener = new MessageAdapter.MyClickListener() {
        @Override
        public void myOnClick(int p, View v) {
            String u = mData.get(p).getFromUser().getUserName();
            Intent i = new Intent();
            if (u.equals("系统消息")) {
                return;
            }
            if (u.equals(JMessageClient.getMyInfo().getUserName())) {
                i.setClass(context, MyInfoActivity.class);
            } else {
                i.putExtra("u", u);
                i.setClass(context, UserInfoActivity.class);
            }
            startActivity(i);
        }
    };


    /**
     * exp message
     */
    private void initExp() {
        list_exp = new ArrayList<>();
        key_exp = new ArrayList<>();
        for (int i = 0; i < DefQqEmoticons.sQqEmoticonKey.length; i++) {
            String s = DefQqEmoticons.sQqEmoticonKey[i];
            key_exp.add(s);
            Map m = new HashMap();
            m.put("pic", DefQqEmoticons.sQqEmoticonHashMap.get(s));
            list_exp.add(m);
        }
        simple_exp = new SimpleAdapter(this, list_exp,
                R.layout.chat_exp_item, new String[]{"pic"},
                new int[]{R.id.img_chat_exp_item});
        gv_exp.setAdapter(simple_exp);

    }

    private void openGallery() {
        GalleryUtil.initGallery(new IHandlerCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<String> photoList) {
                uploadPic(photoList.get(0));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError() {

            }
        }).getBuilder().crop(false).build();
        AndPermission.with(this)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        GalleryUtil.open(activity);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        T.s("No Permission");
                    }
                })
                .start();
    }


    private void uploadPic(String picturePath) {
        if (picturePath != null) {
            File file = new File(picturePath);
            try {
                final Message message = JMessageClient.createGroupImageMessage(ID, file);
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            mAdapter.notifyDataSetChanged();
                        } else {
                            T.s("发送失败");
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                JMessageClient.sendMessage(message);
                mData.add(message);
                mAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mList.setSelection(mAdapter.getCount());
                    }
                }, 100);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
        }
    }

}
