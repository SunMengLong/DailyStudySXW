package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.application.MyApplication;

/**
 * Created by Pooh on 2017/1/12.
 */
public class CircleFagmentLiMian extends Fragment implements ViewPager.OnPageChangeListener {

    private static ViewPager circle_vp_nei;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv=View.inflate(getActivity(), R.layout.circle_fragment_view_nei,null);
        circle_vp_nei = (ViewPager) vv.findViewById(R.id.circle_vp_nei);
        circle_vp_nei.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment;
                if(position==0){
                    fragment=new CircleTopicFragment();
                }else if(position==1){
                    fragment=new CircleHotFragment();
                }else{
                    fragment=new CircleFollowicFragment();
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        circle_vp_nei.setOnPageChangeListener(this);
        //判断将要显示的是哪一个界面
        if(MyApplication.direction2==2){
            circle_vp_nei.setCurrentItem(MyApplication.direction2);
        }
        return vv;
    }

    /**
     * VP的滑动监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Circle_Fragment.getType(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //对外提供的方法
    public static void setCur(int i){
        if(circle_vp_nei!=null) {
            circle_vp_nei.setCurrentItem(i);
        }
    }
}
