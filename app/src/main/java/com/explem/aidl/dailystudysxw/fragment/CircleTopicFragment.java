package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.bean.CircleTopicBean;
import com.explem.aidl.dailystudysxw.http.httpBoolean;
import com.explem.aidl.dailystudysxw.interfaces.ReShowingListener;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.CirclrURL;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Pooh on 2017/1/12.
 */

public class CircleTopicFragment extends BaseFragment{
    //网络判断的boolean类型
    boolean isshow=true;
    private static ViewPager circle_vp_nei;
    private View vv;
    private ViewPager circle_fragment_topic_vp;
    private RecyclerView circle_fragment_topic_recy;
    private CircleTopicBean circleTopicBean;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收传过来的数据
            String data= (String) msg.obj;
            //解析数据
            Gson gson=new Gson();
            circleTopicBean = gson.fromJson(data, CircleTopicBean.class);
            //设置数据
            setData(circleTopicBean);
        }
    };

    private void setData(CircleTopicBean circleTopicBean) {
        //设置图片的集合
        ArrayList<String> listImg=new ArrayList<>();
        for (int i = 0; i < circleTopicBean.getData().getBanner().size(); i++) {
            listImg.add(circleTopicBean.getData().getBanner().get(i).getImg());
        }
        //设置小圆点的集合
    }


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
            CircleTopicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }else {
            //设置为成功界面
            CircleTopicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        //对showingpager作出监听
        showingPage.setShowingPagerListener(new ReShowingListener() {
            @Override
            public void MyReshowingListener(View v) {
                //连接网络时，将状态值设为true,这里的网络判断已经在回调方法中判断
                isshow=true;
                //设置为成功界面
                CircleTopicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        });
        //判断网络是否连接
        if(isshow){
            CircleTopicFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

    @Override
    protected View createSuccessView() {
        //找到布局
        vv = View.inflate(getActivity(), R.layout.circle_fragment_topic_view,null);
        //初始化控件
        initView();
        //请求数据
        getData();
        return vv;
    }

    private void getData() {
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                //发送给Handler
                Message msg = Message.obtain();
                msg.obj=data;
                handler.sendMessage(msg);
            }
        };
        baseData.getDate(true,true, CirclrURL.circle_topic_baseUrl,CirclrURL.circle_topic_url,0,BaseDate.NOMALTIME,BaseDate.getData,null);
    }

    private void initView() {
        circle_fragment_topic_vp = (ViewPager) vv.findViewById(R.id.circle_fragment_topic_vp);
        circle_fragment_topic_recy = (RecyclerView) vv.findViewById(R.id.circle_fragment_topic_recy);
    }
}
