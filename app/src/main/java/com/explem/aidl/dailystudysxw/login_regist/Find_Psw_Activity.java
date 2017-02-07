package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.login_regist.bean.BackInfo;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.explem.aidl.dailystudysxw.R.id.tv_regist_hintinfo;

public class Find_Psw_Activity extends BaseActivity {
    //头部标题
    @InjectView(R.id.tv_common_header_title) TextView tv_common_header_title;
    //电话输入
    @InjectView(R.id.tv_activity_regist_phone) TextView tv_activity_regist_phone;
    //验证码
    @InjectView(R.id.tv_activity_regist_code) TextView tv_activity_regist_code;
    //设置新密码
    @InjectView(R.id.tv_activity_regist_password) TextView tv_activity_regist_password;
    //提示信息
    @InjectView(R.id.tv_findpsw_hintinfo) TextView tv_findpsw_hintinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__psw_);

        ButterKnife.inject(this);

        tv_common_header_title.setText("找回密码");
    }

    @OnClick(R.id.tv_find_psw_mail)           //点击进入邮箱找回密码界面
    public void findPswByMail(){
        JumpUtils.jumpOnly(this,Find_Psw_Mail_Activity.class);
    }

    @OnClick(R.id.iv_common_header_back)      //点击返回
    public void back(){
        finish();
    }

    @OnClick(R.id.tv_activity_findpsw_getcode)//点击获取验证码
    public void mgetCode(){
        LogUtils.showToastA(this,"获取验证码");
        getCode();
    }

    @OnClick(R.id.tv_activity_regist_submit)  //点击提交
    public void submit(){
        regist();
    }

    private void getCode() {

        String phone = tv_activity_regist_phone.getText().toString().trim();
        if (phone.isEmpty()) {
            tv_findpsw_hintinfo.setText("手机号不能为空");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", phone);
        map.put("type", "1");
        map.put("dosubmit", "1");
//        map.put("from", "hiapk");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                BackInfo backInfo = gson.fromJson(data, BackInfo.class);
                tv_findpsw_hintinfo.setText(backInfo.getMsg());
                LogUtils.showToast(Find_Psw_Activity.this, "------" + data);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=forget&a=doforget", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }

    private void regist() {
        String phone = tv_activity_regist_phone.getText().toString().trim();
        String code = tv_activity_regist_code.getText().toString().trim();
        String password = tv_activity_regist_password.getText().toString().trim();
        if (phone.isEmpty()) {
            tv_findpsw_hintinfo.setText("手机号不能为空");
            return;
        }
        if (code.isEmpty()) {
            tv_findpsw_hintinfo.setText("验证码不能为空");
            return;
        }
        if (password.isEmpty()) {
            tv_findpsw_hintinfo.setText("密码不能为空");
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
                Toast.makeText(Find_Psw_Activity.this, registInfo.getMsg(), Toast.LENGTH_SHORT).show();
                JumpUtils.jumpOnly(Find_Psw_Activity.this, LoginActivity.class);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=forget&a=modify", 100, BaseDate.NOTIME, BaseDate.postData, null);
    }

}
