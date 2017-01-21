package com.explem.aidl.dailystudysxw.login_regist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import static com.explem.aidl.dailystudysxw.R.id.my_mail_et;
import static com.explem.aidl.dailystudysxw.R.id.tv_common_header_title;

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
    public void back(){
        finish();
    }

    @OnClick(R.id.my_mail_submit)
    public void submit(){
        String trim = my_mail_et.getText().toString().trim();
        if (trim.isEmpty()){
            Toast.makeText(this, "邮箱为空", Toast.LENGTH_SHORT).show();
        }else{
            //网络请求提交邮箱
        }
    }

}
