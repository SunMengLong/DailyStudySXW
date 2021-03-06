package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.http.httpBoolean;
import com.explem.aidl.dailystudysxw.interfaces.ReShowingListener;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

/**
 * Created by Pooh on 2017/1/12.
 */

public class CircleFollowicFragment extends BaseFragment{
    //网络判断的boolean类型
    boolean isshow=true;
    private static ViewPager circle_vp_nei;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //第一次进入判断网络，如果未联网状态，则将状态值设为false
        if(httpBoolean.checkNetworkState(getActivity())){
            isshow=false;
        }
    }
    @Override
    protected void onload() {
        //判断网络是否连接
        if(httpBoolean.checkNetworkState(getActivity())){
            CircleFollowicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }else {
            //设置为成功界面
            CircleFollowicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        //对showingpager作出监听
        showingPage.setShowingPagerListener(new ReShowingListener() {
            @Override
            public void MyReshowingListener(View v) {
                //连接网络时，将状态值设为true,这里的网络判断已经在回调方法中判断
                isshow=true;
                //设置为成功界面
                CircleFollowicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        });
        //判断网络是否连接
        if(isshow){
            CircleFollowicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

    @Override
    protected View createSuccessView() {
        TextView tv=new TextView(getActivity());
        tv.setText("我是  关注   界面......");
        tv.setTextSize(25);
        //请求网络
        getNet();
        return tv;
    }

    private void getNet() {
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
                Log.i("aaaaaa", "setResultError: ......"+stateLoadError);
            }

            @Override
            public void setResultData(String data) {
                Log.i("aaaaaa", "setResultData: ......"+data);
            }
        };
        baseData.getDate(true, false, "http://www.meirixue.com/", "api.php?c=login&a=index", 100, BaseDate.NOTIME, BaseDate.postData,null);
    }
}
