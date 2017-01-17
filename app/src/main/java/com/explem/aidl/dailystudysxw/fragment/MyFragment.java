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
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pooh on 2017/1/10.
 */

public class MyFragment extends Fragment implements SpringView.OnFreshListener {

    private View vv;
    private String tag;
    //请求数据的页数
    public int page=1;
    //设置是什么时间的请求
    public int first=0;
    public int refrush=1;
    public int more=2;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收数据
            String data= (String) msg.obj;
            typeHandler = msg.arg1;
            Gson gson=new Gson();
            HotBean hotBean=gson.fromJson(data, HotBean.class);
            //请求的最新的数据
            ArrayList<HotBean.DataBean> list= (ArrayList<HotBean.DataBean>) hotBean.getData();
            //装进存储的集合
            if(typeHandler!=more){
                countList.addAll(0,list);
            }else{
                countList.addAll(list);
            }
            //设置数据
            setDataRecy(countList);
        }
    };
    private List<HotBean.DataBean> data;
    private FloatingActionButton floatingActionButton;
    private SpringView hot_myfragment_spr;
    //刷新和加载更多的集合
    ArrayList<HotBean.DataBean> countList=new ArrayList<>();
    private HotRecyAdapter recyAdapter;
    private int typeHandler;

    private void setDataRecy(ArrayList<HotBean.DataBean> list) {
        hot_myfragment_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyAdapter = new HotRecyAdapter(list,getActivity());
        Log.i("aaaaaaaaaa", "setData: ..list..."+list.size());
        if(typeHandler==more){
            recyAdapter.notifyDataSetChanged();
        }else {
            hot_myfragment_recy.setAdapter(recyAdapter);
        }
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
        floatingActionButton = (FloatingActionButton) vv.findViewById(R.id.floatingActionButton);
        hot_myfragment_spr = (SpringView) vv.findViewById(R.id.hot_myfragment_spr);
        //设置头部
        hot_myfragment_spr.setHeader(new DefaultHeader(getActivity()));
        hot_myfragment_spr.setFooter(new DefaultFooter(getActivity()));
        hot_myfragment_spr.setType(SpringView.Type.FOLLOW);
        //设置监听
        hot_myfragment_spr.setListener(this);
        //设置数据
        setData(first);
    }

    /**
     * 悬浮按钮操作
     *
     * @param isShow
     */
    private void showTab(boolean isShow) {
        if (isShow) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
    }


    private void setData(final int type) {
        //悬浮控件的监听
        hot_myfragment_recy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                showTab(dy <= 0);
            }
        });

        //请求网络数据
        //热门数据的请求post
        final HashMap<String,String> map=new HashMap<>();
        Log.i("aaaaaaaaaa", "setData: ....."+page);
        map.put("page",page+"");
        map.put("tid",tag);
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                //发送给Handler
                Message msg=new Message();
                msg.obj=data;
                msg.arg1=type;
                handler.sendMessage(msg);
            }
        };
        baseData.getDate(false,true, CirclrURL.circle_hot_baseUrl,CirclrURL.circle_hot_url,Integer.parseInt(tag)+page, BaseDate.NOMALTIME,BaseDate.postData,map);
    }

    public static Fragment getFragment(String args){
        MyFragment myFragment=new MyFragment();
        Bundle bundler=new Bundle();
        bundler.putString("tag",args);
        myFragment.setArguments(bundler);
        return myFragment;
    }

    @Override
    public void onRefresh() {
        //再次请求网络数据，刷新数据
        page++;
        //设置数据
        setData(refrush);
        //刷新适配器
        recyAdapter.notifyDataSetChanged();

        //隐藏头部
        hot_myfragment_spr.setType(SpringView.Type.FOLLOW);
        hot_myfragment_spr.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        //再次请求网络数据，刷新数据
        page++;
        //设置数据
        setData(more);
        recyAdapter.notifyDataSetChanged();
        //隐藏头部
        hot_myfragment_spr.setType(SpringView.Type.FOLLOW);
        hot_myfragment_spr.onFinishFreshAndLoad();
    }
}
