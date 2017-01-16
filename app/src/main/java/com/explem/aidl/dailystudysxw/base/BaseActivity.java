package com.explem.aidl.dailystudysxw.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.explem.aidl.dailystudysxw.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
