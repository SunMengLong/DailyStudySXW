package com.explem.aidl.dailystudysxw.login_regist;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.login_regist.bean.BackInfo;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.HashMap;

import static android.R.attr.data;

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    public TextView tv_activity_regist_code;
    public TextView tv_activity_regist_phone;
    public TextView tv_activity_regist_password;
    private TextView tv_regist_hintinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String message = (String) msg.obj;
            tv_activity_regist_code.setText(message);
        }
    };

    private void monitorMessage() {
        Uri uri = Uri.parse("content://sms");

        SmsContentObserver smsContentObserver = new SmsContentObserver(handler, this);
        //注册一个内容观察者
        getContentResolver().registerContentObserver(uri, true, smsContentObserver);
    }

    private void initView() {
        TextView tv_common_header_title = (TextView) findViewById(R.id.tv_common_header_title);
        tv_activity_regist_phone = (EditText) findViewById(R.id.tv_activity_regist_phone);
        tv_activity_regist_code = (EditText) findViewById(R.id.tv_activity_regist_code);
        tv_activity_regist_password = (EditText) findViewById(R.id.tv_activity_regist_password);
        tv_regist_hintinfo = (TextView) findViewById(R.id.tv_regist_hintinfo);

        findViewById(R.id.iv_common_header_back).setOnClickListener(this);//点击返回
        findViewById(R.id.tv_activity_regist).setOnClickListener(this);//点击注册
        findViewById(R.id.tv_activity_regist_notice).setOnClickListener(this);//点击须知
        findViewById(R.id.tv_activity_regist_getcode).setOnClickListener(this);//点击获取验证码

        tv_common_header_title.setText("注册");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_header_back://返回
                finish();
                break;

            case R.id.tv_activity_regist://注册
                regist();
                break;

            case R.id.tv_activity_regist_notice:

                break;

            case R.id.tv_activity_regist_getcode://获取验证码
                getCode();
                break;
        }

    }

    private void getCode() {

        String phone = tv_activity_regist_phone.getText().toString().trim();
        if (phone.isEmpty()) {
            tv_regist_hintinfo.setText("手机号不能为空");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", phone);
        map.put("type", "1");
        map.put("dosubmit", "1");
        map.put("from", "hiapk");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                BackInfo backInfo = gson.fromJson(data, BackInfo.class);
                tv_regist_hintinfo.setText(backInfo.getMsg());
                LogUtils.showToast(RegistActivity.this, "------" + data);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=register&a=doregister", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }

    private void regist() {
        String phone = tv_activity_regist_phone.getText().toString().trim();
        String code = tv_activity_regist_code.getText().toString().trim();
        String password = tv_activity_regist_password.getText().toString().trim();
        if (phone.isEmpty()) {
            tv_regist_hintinfo.setText("手机号不能为空");
            return;
        }
        if (code.isEmpty()) {
            tv_regist_hintinfo.setText("验证码不能为空");
            return;
        }
        if (password.isEmpty()) {
            tv_regist_hintinfo.setText("密码不能为空");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", phone);
        map.put("code", code);
        map.put("password", password);
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                BackInfo registInfo = gson.fromJson(data, BackInfo.class);
//                LogUtils.i("regist",registInfo.getMsg());
                Toast.makeText(RegistActivity.this, registInfo.getMsg(), Toast.LENGTH_SHORT).show();
                JumpUtils.jumpOnly(RegistActivity.this, LoginActivity.class);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=register&a=verify", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }
}
