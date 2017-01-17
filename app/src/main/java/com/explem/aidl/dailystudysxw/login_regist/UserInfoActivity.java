package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.cookie.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.inject(this);

        tv_common_header_title.setText("个人信息");

    }

   @OnClick(R.id.my_info_exit)
    public void exit(){
       SharedPreferencesUtils.saveBoolean(this,"isLogedin",false);
       finish();
   }

    @OnClick(R.id.iv_common_header_back)
    public void back(){
        finish();
    }
}
