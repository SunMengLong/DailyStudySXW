package com.explem.aidl.dailystudysxw.login_regist;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    public TextView tv_activity_regist_code;
    public TextView tv_activity_regist_phone;
    public TextView tv_activity_regist_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();

//        monitorMessage();
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
        getContentResolver().registerContentObserver(uri, true,smsContentObserver );
    }

    private void initView() {
        TextView tv_common_header_title = (TextView) findViewById(R.id.tv_common_header_title);
        tv_activity_regist_phone = (EditText) findViewById(R.id.tv_activity_regist_phone);
        tv_activity_regist_code = (EditText) findViewById(R.id.tv_activity_regist_code);
        tv_activity_regist_password = (EditText) findViewById(R.id.tv_activity_regist_password);

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

            case R.id.tv_activity_regist_getcode:
                getCode();
                break;
        }

    }

    private void getCode() {
        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", tv_activity_regist_phone.getText().toString().trim());
        map.put("type", "1");
        map.put("dosubmit", "1");
        map.put("from", "hiapk");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                LogUtils.showToast(RegistActivity.this,"------"+data);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=register&a=doregister", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }

    private void regist() {
        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", tv_activity_regist_phone.getText().toString().trim());
        map.put("code", tv_activity_regist_code.getText().toString().trim());
        map.put("password", tv_activity_regist_password.getText().toString().trim());
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                LogUtils.i("regist",data);
                Gson gson = new Gson();
                BackInfo registInfo = gson.fromJson(data, BackInfo.class);
                Toast.makeText(RegistActivity.this, registInfo.getMsg(), Toast.LENGTH_SHORT).show();
                JumpUtils.jumpOnly(RegistActivity.this,LoginActivity.class);
            }
            //第一个参数为读，第二个参数为写
//                               http://www.meirixue.com/    api.php?c=register&a=verify&code=734975&account=18801273965&password=123456
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=register&a=verify", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }
}
