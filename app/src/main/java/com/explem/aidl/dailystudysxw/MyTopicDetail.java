package com.explem.aidl.dailystudysxw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.fragment.MyFragment;
import com.zhy.autolayout.AutoRelativeLayout;

public class MyTopicDetail extends BaseActivity {

    ImageView detailBackImg;
    ImageView detailWriteImg;
    CollapsingToolbarLayout collapsingtoolbarlayout;
    public static AppBarLayout appBarLayout;
    TabLayout topTitleTabLayout;
    ViewPager tooiccontentviewPager;
    AutoRelativeLayout activitytopicdetail;
    private int nid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        //接收传过来的值
        Intent intent = getIntent();
        nid = intent.getIntExtra("nid", 0);
        Toast.makeText(this, "nid:" + nid, Toast.LENGTH_SHORT).show();
        //初始化控件
        initView();
        //设置数据
        setData();
    }

    private void setData() {
        //添加头部的参数
        topTitleTabLayout.addTab(topTitleTabLayout.newTab().setText("最新"), true);
        topTitleTabLayout.addTab(topTitleTabLayout.newTab().setText("最热"), true);
        //vp的适配器
        FragmentPagerAdapter fAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MyFragment.getFragment(nid+"",MyFragment.TopicDetail,position+1);
            }
            @Override
            public int getCount() {
                return 2;
            }

            /**
             * 此方法解决tabLayout与viewPager进行关联时title不显示的问题
             * @param position
             * @return
             */
            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0){
                    return "最新";
                }
                return "最热";
            }
        };
        tooiccontentviewPager.setAdapter(fAdapter);
        //tabLayout的条目改变监听
        topTitleTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中时，tab.getPosition()获取选中条目的下标
                tooiccontentviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //tabLayout与vp进行关联
        topTitleTabLayout.setTabsFromPagerAdapter(fAdapter);
        topTitleTabLayout.setupWithViewPager(tooiccontentviewPager);
    }

    private void initView() {
        detailBackImg = (ImageView) findViewById(R.id.detailBackImg);
        detailWriteImg = (ImageView) findViewById(R.id.detailWriteImg);
        collapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        topTitleTabLayout = (TabLayout) findViewById(R.id.top_Title_TabLayout);
        tooiccontentviewPager = (ViewPager) findViewById(R.id.tooic_content_viewPager);
        activitytopicdetail = (AutoRelativeLayout) findViewById(R.id.activity_topic_detail);
    }
}
