package com.explem.aidl.dailystudysxw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.bean.CircleItemDetailTitleBean;
import com.explem.aidl.dailystudysxw.fragment.MyFragment;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;

public class MyTopicDetail extends BaseActivity implements View.OnClickListener{

    ImageView detailBackImg;
    ImageView detailWriteImg;
    CollapsingToolbarLayout collapsingtoolbarlayout;
    public static AppBarLayout appBarLayout;
    TabLayout topTitleTabLayout;
    ViewPager tooiccontentviewPager;
    AutoRelativeLayout activitytopicdetail;
    private int nid;
    //网易云账号，直播软件的接口----界面

    //接收头部的请求参数
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String data= (String) msg.obj;
                //解析数据
                Gson gson=new Gson();
                CircleItemDetailTitleBean circleItemDetailTitleBean=gson.fromJson(data, CircleItemDetailTitleBean.class);
                //赋值
                Glide.with(MyTopicDetail.this).load(circleItemDetailTitleBean.getData().getN_small_img()).into(user_image);
                Glide.with(MyTopicDetail.this).load(circleItemDetailTitleBean.getData().getN_big_img()).into(image_bg);
            }
        }
    };
    private ImageView user_image;
    private AutoRelativeLayout circleDetailTitleRela;
    private ImageView image_bg;

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
        //设置监听
        setLitener();
    }

    private void setLitener() {
        detailBackImg.setOnClickListener(this);
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
                //在此请求头部数据
                getTitleDataFromNet(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //tabLayout与vp进行关联
        //topTitleTabLayout.setTabsFromPagerAdapter(fAdapter);
        topTitleTabLayout.setupWithViewPager(tooiccontentviewPager);
    }
    //请求头部的数据
    private void getTitleDataFromNet(int position) {
        //头部数据的baseUrl
        String baseUrl="http://www.meirixue.com";
        //路径
        String path="http://www.meirixue.com/api.php?c=circle&a=getCircleNameInfo";
        //参数
        HashMap<String,String> map=new HashMap<>();
        map.put("nid",nid+"");
        map.put("order",position+"");
        map.put("page",1+"");
        //请求数据
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
                //请求错误
                Toast.makeText(MyTopicDetail.this, "请求头部参数出错...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setResultData(String data) {
                Message msg=Message.obtain();
                msg.obj=data;
                msg.what=0;
                handler.sendMessage(msg);
            }
        };
        baseData.getDate(false,false,baseUrl,path,nid+position,BaseDate.NOMALTIME,BaseDate.postData,map);
    }

    private void initView() {
        image_bg = (ImageView) findViewById(R.id.image_bg);
        circleDetailTitleRela = (AutoRelativeLayout) findViewById(R.id.circleDetailTitleRela);
        user_image = (ImageView) findViewById(R.id.user_image);
        detailBackImg = (ImageView) findViewById(R.id.detailBackImg);
        detailWriteImg = (ImageView) findViewById(R.id.detailWriteImg);
        collapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        topTitleTabLayout = (TabLayout) findViewById(R.id.top_Title_TabLayout);
        tooiccontentviewPager = (ViewPager) findViewById(R.id.tooic_content_viewPager);
        activitytopicdetail = (AutoRelativeLayout) findViewById(R.id.activity_topic_detail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detailBackImg:
                finish();
                break;
            default:
                break;
        }
    }
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
