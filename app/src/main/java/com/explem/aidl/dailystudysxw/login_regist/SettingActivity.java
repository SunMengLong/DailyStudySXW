package com.explem.aidl.dailystudysxw.login_regist;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64DataException;
import android.widget.ImageView;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.DataCleanManager;
import com.explem.aidl.dailystudysxw.utils.DataClearManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;

    @InjectView(R.id.setting_textview_cachesize)
    TextView setting_textview_cachesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        tv_common_header_title.setText("设置");

        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            setting_textview_cachesize.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.iv_common_header_back)
    public void back(){
        finish();
    }

    @OnClick(R.id.setting_clear)
    public void clear() {
        DataCleanManager.deleteFilesByDirectory(BaseDate.cacheDir);
        setting_textview_cachesize.setText("0.0");
    }
}
