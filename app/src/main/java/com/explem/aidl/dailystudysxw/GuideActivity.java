package com.explem.aidl.dailystudysxw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.explem.aidl.dailystudysxw.fragment.Guide_Fragment1;
import com.explem.aidl.dailystudysxw.fragment.Guide_Fragment2;
import com.explem.aidl.dailystudysxw.fragment.Guide_Fragment3;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private RelativeLayout activity_guide;
    private  boolean tag=true;
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1 = msg.arg1;
            Jump();
        }
    };

    private void Jump() {
        Intent in =new Intent(GuideActivity.this,MainActivity.class);
        startActivity(in);
        finish();
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                   case 0:
                       edit = sharedPreferences.edit();
                       edit.putBoolean("flag",true);
                       edit.commit();
                       guide_vp.setVisibility(View.VISIBLE);
                       guide_img.setVisibility(View.GONE);
                       guide_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                           @Override
                           public Fragment getItem(int position) {
                               return fragmentList.get(position);
                           }
                           @Override
                           public int getCount() {
                               return fragmentList.size();
                           }
                       });
                       break;
                case 1:
                    guide_img.setScaleType(ImageView.ScaleType.FIT_XY);
                    guide_img.setImageResource(R.mipmap.start1);
                    //第二次睡2秒直接跳入MainActivity
                    getTiao();
                    guide_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                             tag=false;
                            if(tag=false){
                                h.removeCallbacksAndMessages(null);
                                Jump();
                            }
                        }
                    });
                       break;
            }
        }

        private void getTiao() {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg2 = Message.obtain();
                    int arg1 = msg2.arg1;
                     h.sendMessage(msg2);
                }
            }.start();

        }
    };
    private ViewPager guide_vp;
    private ImageView guide_img;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }


    private void initView() {
        getData();
        guide_vp = (ViewPager) findViewById(R.id.Guide_vp);
        guide_img = (ImageView) findViewById(R.id.Guide_img);
        sharedPreferences = getSharedPreferences("isFirst",MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag", false);
        //第一次的登录
        if(!flag){
            jumpFirst();
        }else{
            jumpSecond();
     }
      }
    //得到Fragment
    private void getData() {
        fragmentList = new ArrayList<>();
        Guide_Fragment1 f1=new Guide_Fragment1();
        Guide_Fragment2 f2=new Guide_Fragment2();
        Guide_Fragment3 f3=new Guide_Fragment3();
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
    }

    //第一次登录
    private void jumpFirst() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.obtainMessage(0).sendToTarget();

            }
        }.start();

    }
    //第二次登录
    public void jumpSecond() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.obtainMessage(1).sendToTarget();
            }
        }.start();
    }
}
