package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv_common_header_title = (TextView) findViewById(R.id.tv_common_header_title);

        tv_common_header_title.setText("登录");
    }
}
