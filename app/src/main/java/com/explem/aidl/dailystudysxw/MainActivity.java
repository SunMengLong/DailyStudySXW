package com.explem.aidl.dailystudysxw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.explem.aidl.dailystudysxw.adapter.homeFragmentVpAdapter;
import com.explem.aidl.dailystudysxw.base.BaseActivity;
import com.explem.aidl.dailystudysxw.view.LazyViewPager;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, LazyViewPager.OnPageChangeListener {

    private LazyViewPager home_fragment_vp;
    private RadioGroup rg_home_fragment;
    private boolean ff;
    public static  FragmentManager supportFragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //设置数据的方法
        setData();
        //沉浸式状态栏
        trpe();
    }



    private void setData() {
        home_fragment_vp.setAdapter(new homeFragmentVpAdapter(getSupportFragmentManager()));
        rg_home_fragment = (RadioGroup) findViewById(R.id.rg_home_fragment);
        //设置监听事件
        home_fragment_vp.setOnPageChangeListener(this);
        rg_home_fragment.setOnCheckedChangeListener(this);

        supportFragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        home_fragment_vp = (LazyViewPager) findViewById(R.id.home_fragment_vp);
    }

    /**
     * ViewPager的滑动监听重写的三个方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置radiobutton的是否选中
        for (int i = 0; i < rg_home_fragment.getChildCount(); i++) {
            RadioButton rb= (RadioButton) rg_home_fragment.getChildAt(i);
            if(position==i){
                rb.setChecked(true);
                rb.setTextColor(Color.RED);
            }else{
                rb.setChecked(false);
                rb.setTextColor(Color.parseColor("#999999"));
            }
        }
    }
    /**
     * RadioGroup改变的监听方法
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        for (int j = 0; j < rg_home_fragment.getChildCount(); j++) {
            if(rg_home_fragment.getChildAt(j).getId()==i){
                home_fragment_vp.setCurrentItem(j);
            }
        }
    }
    //沉浸式状态栏
    public void trpe(){
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}
