package com.explem.aidl.dailystudysxw.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.explem.aidl.dailystudysxw.fragment.Circle_Fragment;
import com.explem.aidl.dailystudysxw.fragment.Classify_Fragment;
import com.explem.aidl.dailystudysxw.fragment.Home_Fragment;
import com.explem.aidl.dailystudysxw.fragment.Mine_Fragment;

/**
 * Created by Pooh on 2017/1/10.
 */

public class homeFragmentVpAdapter extends FragmentPagerAdapter {
    public homeFragmentVpAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0){
            fragment=new Home_Fragment();
        }else if(position==1){
            fragment=new Classify_Fragment();
        }else if(position==2){
            fragment=new Circle_Fragment();
        }else if(position==3){
            fragment=new Mine_Fragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
