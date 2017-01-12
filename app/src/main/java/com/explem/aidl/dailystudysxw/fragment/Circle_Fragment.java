package com.explem.aidl.dailystudysxw.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.explem.aidl.dailystudysxw.R;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Circle_Fragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private Fragment jintaiFragment;
    private FragmentTransaction fragmentTransaction;
    private View vv;
    private static RadioGroup circle_rg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = View.inflate(getActivity(), R.layout.circle_fragment_view,null);
        initView();
        FragmentManager fragmentManager=getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.jintai_framlayout,new CircleFagmentLiMian());
        fragmentTransaction.commit();
        return vv;
    }

    private void initView() {
        circle_rg = (RadioGroup) vv.findViewById(R.id.circle_rg);
        //改变监听
        circle_rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        for (int j = 0; j < circle_rg.getChildCount(); j++) {
            RadioButton rb= (RadioButton) circle_rg.getChildAt(j);
            if(i==circle_rg.getChildAt(j).getId()){
                rb.setTextColor(Color.RED);
                CircleFagmentLiMian.setCur(j);
            }else{
                rb.setTextColor(Color.BLACK);
            }
        }
    }

    //对外提供的方法
    public static void getType(int i){
        for (int j = 0; j < circle_rg.getChildCount(); j++) {
            RadioButton rb= (RadioButton) circle_rg.getChildAt(j);
            if(i==j){
                rb.setTextColor(Color.RED);
            }else{
                rb.setTextColor(Color.BLACK);
            }
        }
    }
}
