package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.login_regist.LoginActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

import java.util.HashMap;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Mine_Fragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        getActivity().findViewById(R.id.autoll_fragment_mine_top).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_mycourse).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_collections).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_coupon).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_message).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_feedback).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autoll_fragment_mine_top:
                LogUtils.showToast(getActivity(),"跳转到登录界面");
                JumpUtils.jumpOnly(getActivity(), LoginActivity.class);
                break;

            case R.id.autoll_fragment_mine_mycourse:
                LogUtils.showToast(getActivity(),"跳转到我的课程");
                break;

            case R.id.autoll_fragment_mine_collections:
                LogUtils.showToast(getActivity(),"跳转到收藏");
                break;

            case R.id.autoll_fragment_mine_coupon:
                LogUtils.showToast(getActivity(),"跳转到优惠券");
                break;
            case R.id.autoll_fragment_mine_message:
                LogUtils.showToast(getActivity(),"跳转到我的消息");
                break;
            case R.id.autoll_fragment_mine_feedback:
                LogUtils.showToast(getActivity(),"跳转到意见反馈");
                break;
            case R.id.autoll_fragment_mine_setting:
                LogUtils.showToast(getActivity(),"跳转到设置界面");
                break;
        }
    }

//        private void initData() {
//        HashMap<String, String> map=new HashMap<>();
//        //用户名及密码
//        map.put("userName","13121980161");
//        map.put("password","123456789");
//        map.put("dosubmit","1");
//        new BaseDate() {
//            @Override
//            protected void setResultError(ShowingPage.StateType stateLoadError) {
//
//            }
//
//            @Override
//            public void setResultData(String data) {
//                Toast.makeText(getActivity(), "--"+data, Toast.LENGTH_SHORT).show();
//            }
//            //第一个参数为读，第二个参数为写
//        }.getDate(false,true,"http://www.meirixue.com/","api.php?c=login&a=index",100,BaseDate.NOTIME,BaseDate.postData,map);
//    }
}








    //    @Override
//    protected void onload() {
//        Mine_Fragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
//    }
//    @Override
//    protected View createSuccessView() {
//
//
//        //请求网络的数据
////        initData();
//        return view;
//    }


