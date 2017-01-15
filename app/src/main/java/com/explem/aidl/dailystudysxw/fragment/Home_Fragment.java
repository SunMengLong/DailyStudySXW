package com.explem.aidl.dailystudysxw.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Home_Fragment extends BaseFragment{
    @Override
    protected void onload() {
            //设置为成功界面
            Home_Fragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
         TextView tv=new TextView(getActivity());

        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }
            @Override
            public void setResultData(String data) {
//                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        }.getDate(true,true,"http://japi.juhe.cn/","http://japi.juhe.cn/qqevaluate/qq?key=96efc220a4196fafdfade0c9d1e897ac&qq=295424589",0,BaseDate.NOTIME,BaseDate.getData,null);
        tv.setText("这个是主页！");
        tv.setTextSize(20);
       return tv;
    }
}
