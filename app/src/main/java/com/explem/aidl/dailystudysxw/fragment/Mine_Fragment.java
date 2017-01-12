package com.explem.aidl.dailystudysxw.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

import java.util.HashMap;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Mine_Fragment extends BaseFragment{
    @Override
    protected void onload() {
        Mine_Fragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }
    @Override
    protected View createSuccessView() {
        TextView tv=new TextView(getActivity());
        tv.setText("这个是”我的“界面！");
        tv.setTextSize(20);
        //请求网络的数据
        initData();
        return tv;
    }

    private void initData() {
        HashMap<String, String> map=new HashMap<>();
        //用户名及密码
        map.put("userName","13121980161");
        map.put("password","123456789");
        map.put("dosubmit","1");
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Toast.makeText(getActivity(), "--"+data, Toast.LENGTH_SHORT).show();
            }
            //第一个参数为只读不写，第二个参数为只写不读
        }.getDate(false,true,"http://www.meirixue.com/","api.php?c=login&a=index",100,BaseDate.NOTIME,BaseDate.postData,map);
    }
}
