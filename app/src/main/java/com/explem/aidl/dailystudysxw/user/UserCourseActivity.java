package com.explem.aidl.dailystudysxw.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UserCourseActivity extends AutoLayoutActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;
    @InjectView(R.id.iv_common_header_manage)
    TextView iv_common_header_manage;
    @InjectView(R.id.tab_activity_course)
    TabLayout tab_activity_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_course);

        ButterKnife.inject(this);

        initView();

        getSupportFragmentManager().beginTransaction().add(R.id.framelayout_mycourse,new Fragment_Mycourse().getFragment("0")).commit();
    }

    private void initView() {
        tv_common_header_title.setText("我的课程");
        iv_common_header_manage.setVisibility(View.VISIBLE);
        iv_common_header_manage.setText("管理");

        tab_activity_course.addTab(tab_activity_course.newTab().setText("已学完"),true);
        tab_activity_course.addTab(tab_activity_course.newTab().setText("正在学"),false);
        tab_activity_course.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = new Fragment_Mycourse().getFragment(tab.getPosition()+"");
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout_mycourse,fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick(R.id.iv_common_header_back)
    public void back(){
        this.finish();
    }

    @OnClick(R.id.iv_common_header_manage)
    public void manage(){

    }

}
