package com.explem.aidl.dailystudysxw.login_regist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.cookie.SharedPreferencesUtils;
import com.explem.aidl.dailystudysxw.login_regist.bean.BackInfo;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public EditText tv_activity_login_phone;
    public EditText tv_activity_login_password;
    public TextView tv_activity_login_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {
        TextView tv_common_header_title = (TextView) findViewById(R.id.tv_common_header_title);
        tv_activity_login_phone = (EditText) findViewById(R.id.tv_activity_login_phone);
        tv_activity_login_password = (EditText) findViewById(R.id.tv_activity_login_password);
        tv_activity_login_info = (TextView) findViewById(R.id.tv_activity_login_info);

        findViewById(R.id.tv_activity_login).setOnClickListener(this);
        findViewById(R.id.tv_activity_login_regist).setOnClickListener(this);
        findViewById(R.id.iv_activity_login_qq).setOnClickListener(this);
        findViewById(R.id.iv_common_header_back).setOnClickListener(this);
        findViewById(R.id.tv_activity_login_forgetpassword).setOnClickListener(this);
        tv_common_header_title.setText("登录");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_activity_login://登录
                login();
                break;

            case R.id.tv_activity_login_regist://注册
                JumpUtils.jumpOnly(LoginActivity.this, RegistActivity.class);
                break;

            case R.id.iv_activity_login_qq://第三方QQ登录

                break;

            case R.id.tv_activity_login_forgetpassword://忘记密码
                JumpUtils.jumpOnly(this,Find_Psw_Activity.class);
                break;

            case R.id.iv_common_header_back://返回
                finish();
                break;
        }
    }

    private void login() {

        String phone = tv_activity_login_phone.getText().toString().trim();
        String password = tv_activity_login_password.getText().toString().trim();

        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("userName", phone);
        map.put("password", password);
        map.put("dosubmit", "1");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }

            @Override
            public void setResultData(String data) {
//                Toast.makeText(LoginActivity.this, "--" + data, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                BackInfo backInfo = gson.fromJson(data, BackInfo.class);
                int status = backInfo.getStatus();
                if (200 != status) {
                    tv_activity_login_info.setText(backInfo.getMsg());
                }else {
                    Toast.makeText(LoginActivity.this, backInfo.getMsg(), Toast.LENGTH_SHORT).show();
                    //将登录以后的信息存入sharedpreferences中
                    SharedPreferencesUtils.saveBoolean(LoginActivity.this,"isLogedin",true);
                    SharedPreferencesUtils.saveString(LoginActivity.this,"phone",backInfo.getData().getUser_phone());
                    SharedPreferencesUtils.saveString(LoginActivity.this,"name",backInfo.getData().getUser_name());
                    SharedPreferencesUtils.saveString(LoginActivity.this,"sex",backInfo.getData().getUser_sex());
                    SharedPreferencesUtils.saveString(LoginActivity.this,"img",backInfo.getData().getUser_middle_log());
                    finish();
                }
                LogUtils.i("login", "------" + data);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, true, "http://www.meirixue.com/", "api.php?c=login&a=index", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }
}
