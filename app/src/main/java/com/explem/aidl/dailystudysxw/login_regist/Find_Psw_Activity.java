package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Find_Psw_Activity extends BaseActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__psw_);

        ButterKnife.inject(this);

        tv_common_header_title.setText("找回密码");
    }

    @OnClick(R.id.tv_find_psw_mail)
    public void findPswByMail(){
        JumpUtils.jumpOnly(this,Find_Psw_Mail_Activity.class);
    }

    @OnClick(R.id.iv_common_header_back)
    public void back(){
        finish();
    }

    @OnClick(R.id.tv_activity_findpsw_getcode)
    public void getCode(){

    }
}
