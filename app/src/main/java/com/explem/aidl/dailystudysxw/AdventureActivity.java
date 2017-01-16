package com.explem.aidl.dailystudysxw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

/**
 * 详情界面
 * @author XuJiaJian
 */

public class AdventureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView lianjie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
         initView();
    }

    private void initView() {
       back=(ImageView) findViewById(R.id.back);
        lianjie = (ImageView) findViewById(R.id.lianjie);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.lianjie:
                PopupWindow pop =new PopupWindow();
                break;
        }
    }
}
