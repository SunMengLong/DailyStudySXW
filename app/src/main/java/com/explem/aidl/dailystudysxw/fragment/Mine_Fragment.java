package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.cookie.SharedPreferencesUtils;
import com.explem.aidl.dailystudysxw.login_regist.LoginActivity;
import com.explem.aidl.dailystudysxw.login_regist.UserInfoActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

import java.util.HashMap;

import static com.explem.aidl.dailystudysxw.R.id.ll_my_unloged_top;
import static com.explem.aidl.dailystudysxw.R.id.rl_my_logedin_top;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Mine_Fragment extends Fragment implements View.OnClickListener {

    public TextView tv_fragment_mine_name;
    private ImageView civ_fragment_mine_icon;
    public boolean isLogedin;
    private View ll_my_unloged_top;
    private View rl_my_logedin_top;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.i("mine", "onActivityCreated");
        initView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        isLogedin = SharedPreferencesUtils.getBoolean(getActivity(), "isLogedin", false);
        String phone = SharedPreferencesUtils.getString(getActivity(), "phone", "-1");
        LogUtils.i("mine", "onResume"+isLogedin);
        if (isLogedin) {
            rl_my_logedin_top.setVisibility(View.VISIBLE);
            ll_my_unloged_top.setVisibility(View.GONE);
            tv_fragment_mine_name.setText(phone);
            civ_fragment_mine_icon.setImageResource(R.mipmap.boy);
        }else {
            rl_my_logedin_top.setVisibility(View.GONE);
            ll_my_unloged_top.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        LogUtils.i("mine", "onResume");
    }

    private void initView() {
        getActivity().findViewById(R.id.autoll_fragment_mine_top).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_mycourse).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_collections).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_coupon).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_message).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_feedback).setOnClickListener(this);
        getActivity().findViewById(R.id.autoll_fragment_mine_setting).setOnClickListener(this);

        tv_fragment_mine_name = (TextView) getActivity().findViewById(R.id.tv_fragment_mine_name);
        civ_fragment_mine_icon = (ImageView) getActivity().findViewById(R.id.civ_fragment_mine_icon);
        //没有登录的布局
        ll_my_unloged_top = (View) getActivity().findViewById(R.id.ll_my_unloged_top);
        //登录以后的布局
        rl_my_logedin_top = (View) getActivity().findViewById(R.id.rl_my_logedin_top);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autoll_fragment_mine_top:
//                LogUtils.showToast(getActivity(), "跳转到登录界面");
                if (isLogedin) {//如果登录，跳转到个人详情界面

                    JumpUtils.jumpOnly(getActivity(), UserInfoActivity.class);
                } else {//没有登录，跳转到登录界面

                    JumpUtils.jumpOnly(getActivity(), LoginActivity.class);
                }
                break;

            case R.id.autoll_fragment_mine_mycourse:
                LogUtils.showToast(getActivity(), "跳转到我的课程");
                break;

            case R.id.autoll_fragment_mine_collections:
                LogUtils.showToast(getActivity(), "跳转到收藏");
                break;

            case R.id.autoll_fragment_mine_coupon:
                LogUtils.showToast(getActivity(), "跳转到优惠券");
                break;
            case R.id.autoll_fragment_mine_message:
                LogUtils.showToast(getActivity(), "跳转到我的消息");
                break;
            case R.id.autoll_fragment_mine_feedback:
                LogUtils.showToast(getActivity(), "跳转到意见反馈");
                break;
            case R.id.autoll_fragment_mine_setting:
                LogUtils.showToast(getActivity(), "跳转到设置界面");
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


