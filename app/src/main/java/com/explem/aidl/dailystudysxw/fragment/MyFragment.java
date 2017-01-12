package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pooh on 2017/1/10.
 */

public class MyFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //得到传过来的数据
        String tag=getArguments().getString("tag");
        TextView tv=new TextView(getActivity());
        tv.setText(tag);
        tv.setTextSize(50);
        return tv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment getFragment(String args){
        MyFragment myFragment=new MyFragment();
        Bundle bundler=new Bundle();
        bundler.putString("tag",args);
        myFragment.setArguments(bundler);
        return myFragment;
    }
}
