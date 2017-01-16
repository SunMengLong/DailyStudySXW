package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.adapter.HotRecyAdapter;
import com.explem.aidl.dailystudysxw.bean.HotBean;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.CirclrURL;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pooh on 2017/1/10.
 */

public class MyFragment extends Fragment{

    private View vv;
    private String tag;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收数据
            String data= (String) msg.obj;

            Gson gson=new Gson();
            HotBean hotBean=gson.fromJson(data, HotBean.class);
            //设置数据
            setDataRecy(hotBean);
        }
    };
    private List<HotBean.DataBean> data;

    private void setDataRecy(HotBean hotBean) {
        hot_myfragment_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<HotBean.DataBean> list= (ArrayList<HotBean.DataBean>) hotBean.getData();
        data = hotBean.getData();
        HotRecyAdapter adapter=new HotRecyAdapter(list,getActivity());
        hot_myfragment_recy.setAdapter(adapter);
    }

    private RecyclerView hot_myfragment_recy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //得到传过来的数据
        tag = getArguments().getString("tag");

        //初始化布局
        vv = View.inflate(getActivity(), R.layout.hot_myfragment_view,null);
        return vv;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        hot_myfragment_recy = (RecyclerView) vv.findViewById(R.id.hot_myfragment_recy);
        //设置数据
        setData();
    }

    private void setData() {
        //请求网络数据
        //热门数据的请求post
        final HashMap<String,String> map=new HashMap<>();
        map.put("page","1");
        map.put("tid",tag);
        Log.i("ddddd", "onCreateView: ...."+tag);
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                //发送给Handler
                Message msg=new Message();
                msg.obj=data;
                handler.sendMessage(msg);
            }
        };
        baseData.getDate(false,true, CirclrURL.circle_hot_baseUrl,CirclrURL.circle_hot_url,Integer.parseInt(tag), BaseDate.NOMALTIME,BaseDate.postData,map);
    }

    public static Fragment getFragment(String args){
        MyFragment myFragment=new MyFragment();
        Bundle bundler=new Bundle();
        bundler.putString("tag",args);
        myFragment.setArguments(bundler);
        return myFragment;
    }
}
