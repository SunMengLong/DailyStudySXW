package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.application.MyApplication;

/**
 * Created by Pooh on 2017/1/11.
 */

public class Classify_Fragment extends Fragment{
    private FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv=View.inflate(getActivity(), R.layout.classify_fragment_view,null);
        FragmentManager fragmentManager=getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fenlei_framlayout,new ClassifyFragmentLiMian());
        fragmentTransaction.commit();

        MyApplication.direction2=-1;
        return vv;
    }
}
