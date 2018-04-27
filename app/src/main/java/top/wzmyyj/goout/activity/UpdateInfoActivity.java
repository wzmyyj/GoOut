package top.wzmyyj.goout.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseActivity;
import top.wzmyyj.goout.tools.J;
import top.wzmyyj.goout.utils.gallery.GalleryUtil;
import top.wzmyyj.wzm_sdk.tools.T;


public class UpdateInfoActivity extends BaseActivity {

    private Toolbar mToolbar;


    private LinearLayout ll_m_1;
    private LinearLayout ll_m_2;
    private LinearLayout ll_m_3;
    private LinearLayout ll_m_4;
    private LinearLayout ll_m_5;

    private ImageView img_m_1;
    private TextView tv_m_2;
    private TextView tv_m_3;
    private TextView tv_m_4;
    private TextView tv_m_5;


    private LinearLayout ll_p_1;
    private LinearLayout ll_p_2;
    private LinearLayout ll_p_3;
    private LinearLayout ll_p_4;

    private TextView tv_p_2;
    private TextView tv_p_3;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_info);
        mToolbar = findViewById(R.id.toolbar);

        ll_m_1 = findViewById(R.id.ll_m_1);
        ll_m_2 = findViewById(R.id.ll_m_2);
        ll_m_3 = findViewById(R.id.ll_m_3);
        ll_m_4 = findViewById(R.id.ll_m_4);
        ll_m_5 = findViewById(R.id.ll_m_5);

        img_m_1 = findViewById(R.id.img_m_1);
        tv_m_2 = findViewById(R.id.tv_m_2);
        tv_m_3 = findViewById(R.id.tv_m_3);
        tv_m_4 = findViewById(R.id.tv_m_4);
        tv_m_5 = findViewById(R.id.tv_m_5);

        ll_p_1 = findViewById(R.id.ll_p_1);
        ll_p_2 = findViewById(R.id.ll_p_2);
        ll_p_3 = findViewById(R.id.ll_p_3);
        ll_p_4 = findViewById(R.id.ll_p_4);

        tv_p_2 = findViewById(R.id.tv_p_2);
        tv_p_3 = findViewById(R.id.tv_p_3);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        setInfo(JMessageClient.getMyInfo());


    }

    private void setInfo(UserInfo info) {
        if (info == null) return;
        tv_m_2.setText(info.getNickname());
        tv_m_3.setText(info.getSignature());
        tv_m_4.setText(J.getGender(info));
        tv_m_5.setText(info.getRegion());
        info.getAvatarBitmap(new GetAvatarBitmapCallback() {
            @Override
            public void gotResult(int i, String s, Bitmap bitmap) {
                if (bitmap != null) {
                    img_m_1.setImageBitmap(bitmap);
                } else {
                    img_m_1.setImageResource(R.mipmap.temp);
                }
            }
        });
    }


    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_m_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        ll_m_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(UserInfo.Field.nickname);
            }
        });
        ll_m_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(UserInfo.Field.signature);
            }
        });
        ll_m_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(UserInfo.Field.gender);
            }
        });
        ll_m_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(UserInfo.Field.region);
            }
        });
        ll_p_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, UpdatePasswordActivity.class);
                startActivity(i);
            }
        });


    }

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private void showDialog(UserInfo.Field what) {
        if (what == UserInfo.Field.gender) {
            showSingleChoiceDialog();
        } else {
            showEditTextDialog(what);
        }
    }

    private void showSingleChoiceDialog() {
        final String[] items = new String[]{"男", "女", "保密"};
        int checkedIndex = 1;
        UserInfo info = JMessageClient.getMyInfo();
        if (info.getGender() == UserInfo.Gender.male) {
            checkedIndex = 0;
        } else if (info.getGender() == UserInfo.Gender.female) {
            checkedIndex = 1;
        } else {
            checkedIndex = 2;
        }
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndex)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update(JMessageClient.getMyInfo(), items[which],
                                UserInfo.Field.gender);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showEditTextDialog(final UserInfo.Field what) {
        String h = "在此输入您的";
        switch (what) {
            case nickname:
                h = h + "昵称";
                break;
            case signature:
                h = h + "个人介绍";
                break;
            case region:
                h = h + "地址";
                break;
        }
        final QMUIDialog.EditTextDialogBuilder builder
                = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("编辑：")
                .setPlaceholder(h)
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            update(JMessageClient.getMyInfo(), text.toString(), what);
                            dialog.dismiss();
                        } else {

                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }


    private void update(UserInfo info, String s, UserInfo.Field what) {
        if (TextUtils.isEmpty(s)) {
            T.s("不能为空");
            return;
        }
        switch (what) {
            case nickname:
                info.setNickname(s);
                break;
            case signature:
                info.setSignature(s);
            case gender:
                if (s.equals("男")) {
                    info.setGender(UserInfo.Gender.male);
                } else if (s.equals("女")) {
                    info.setGender(UserInfo.Gender.female);
                } else {
                    info.setGender(UserInfo.Gender.unknown);
                }
            case region:
                info.setRegion(s);
        }

        JMessageClient.updateMyInfo(what, info, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    T.s("update success");
                    setInfo(JMessageClient.getMyInfo());
                } else {
                    T.s("update fail");
                }
            }
        });
    }

    private void updateAvatar(String picturePath) {
        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();
        if (picturePath == null)
            return;
        File file = new File(picturePath);
        JMessageClient.updateUserAvatar(file,
                new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            tipDialog.dismiss();
                            T.s("update success");
                            setInfo(JMessageClient.getMyInfo());
                        } else {
                            tipDialog.dismiss();
                            T.s("update fail");
                        }
                    }
                });
    }


    private void openGallery() {
        if (GalleryUtil.getGallery() == null) {
            initGallery();
        }

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


    private void initGallery() {

        GalleryUtil.init("top.wzmyyj.goout.FileProvider", "/GoOut/head", new IHandlerCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<String> photoList) {
                updateAvatar(photoList.get(0));
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
        });
    }


}
