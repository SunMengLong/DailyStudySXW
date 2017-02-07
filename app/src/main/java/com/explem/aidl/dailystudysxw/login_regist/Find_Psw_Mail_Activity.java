package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.login_regist.bean.BackInfo;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import static com.explem.aidl.dailystudysxw.R.id.my_mail_et;
import static com.explem.aidl.dailystudysxw.R.id.tv_activity_regist_phone;
import static com.explem.aidl.dailystudysxw.R.id.tv_common_header_title;
import static com.explem.aidl.dailystudysxw.R.id.tv_findpsw_hintinfo;
import static com.explem.aidl.dailystudysxw.R.mipmap.phone;

public class Find_Psw_Mail_Activity extends AppCompatActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;
    @InjectView(R.id.my_mail_et)
    EditText my_mail_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__psw__mail_);

        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        tv_common_header_title.setText("找回密码-邮箱");
    }

    @OnClick(R.id.iv_common_header_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.my_mail_submit)//点击提交
    public void submit() {
        getCode();
    }
    @OnClick(R.id.my_mali_find_delete)
    public  void delete(){
        my_mail_et.setText("");
    }

    private void getCode() {
        String mail = my_mail_et.getText().toString().trim();
        if (mail.isEmpty()) {
            Toast.makeText(this, "邮箱为空", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        //用户名及密码
        map.put("account", mail);
        map.put("type", "2");
        map.put("dosubmit", "1");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                BackInfo backInfo = gson.fromJson(data, BackInfo.class);
                Toast.makeText(Find_Psw_Mail_Activity.this, backInfo.getMsg(), Toast.LENGTH_SHORT).show();
//                LogUtils.showToast(Find_Psw_Mail_Activity.this, "------" + data);
            }
            //第一个参数为读，第二个参数为写
        }.getDate(false, false, "http://www.meirixue.com/", "api.php?c=forget&a=doforget", 100, BaseDate.NOTIME, BaseDate.postData, map);
    }

}
