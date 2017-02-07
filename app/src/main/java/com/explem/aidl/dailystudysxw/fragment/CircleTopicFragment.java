package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.MyTopicDetail;
import com.explem.aidl.dailystudysxw.adapter.CirFrTopicAdapter;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.bean.CircleTopicBean;
import com.explem.aidl.dailystudysxw.http.httpBoolean;
import com.explem.aidl.dailystudysxw.interfaces.CirTopicOnClick;
import com.explem.aidl.dailystudysxw.interfaces.ReShowingListener;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.CirclrURL;
import com.explem.aidl.dailystudysxw.view.MyRoolViewPager;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

/**
 * Created by Pooh on 2017/1/12.
 */

public class CircleTopicFragment extends BaseFragment implements MyRoolViewPager.OnPageClickListener, CirTopicOnClick, SpringView.OnFreshListener {
    //网络判断的boolean类型
    boolean isshow=true;
    private static ViewPager circle_vp_nei;
    private View vv;
    private MyRoolViewPager circle_fragment_topic_vp;
    private RecyclerView circle_fragment_topic_recy;
    private RecyclerView circle_fragment_topic_recy1;
    private CircleTopicBean circleTopicBean;
    //定义一个我的圈子的集合
    ArrayList<CircleTopicBean.DataBean.CircleBean> lisyMine=new ArrayList<>();
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
    private LinearLayout circle_fragment_topic_vp_lin;
    private CirFrTopicAdapter cirFrTopicAdapter;
    private SpringView circle_fragment_topic_spring;
    private LinearLayout circle_fragment_topic_mylin;
    private ArrayList<CircleTopicBean.DataBean.CircleBean> list;

    private void setData(CircleTopicBean circleTopicBean) {
        //轮播的数据适配器
        setDataLunBo(circleTopicBean);
        //rece的数据
        setDataRecy(circleTopicBean);
    }

    private void setDataRecy(CircleTopicBean circleTopicBean) {
        circle_fragment_topic_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = (ArrayList<CircleTopicBean.DataBean.CircleBean>) circleTopicBean.getData().getCircle();
        //循环遍历集合，只要“我的圈子”集合当中有的都删除
        if(lisyMine!=null){
            for (int i = lisyMine.size()-1; i >=0 ; i--) {
                for (int j = list.size()-1; j >=0; j--) {
                    if(lisyMine.get(i).getN_title().equals(list.get(j).getN_title())){
                        list.remove(j);
                    }
                }
            }
        }
        cirFrTopicAdapter = new CirFrTopicAdapter(getActivity(), list,CirFrTopicAdapter.hotCircle);
        cirFrTopicAdapter.setOnClick(CircleTopicFragment.this);
        circle_fragment_topic_recy.setAdapter(cirFrTopicAdapter);
        //设置头部和地步
        circle_fragment_topic_spring.setHeader(new DefaultHeader(getActivity()));
        circle_fragment_topic_spring.setType(SpringView.Type.FOLLOW);
        circle_fragment_topic_spring.setListener(this);

        setMyCircle(circleTopicBean);
    }

    //设置“我的圈子的数据适配器”
    public void setMyCircle(CircleTopicBean circleTopicBean){
        if(lisyMine!=null&&lisyMine.size()>0){
            Log.i("aaa", "setMyCircle: ...");
            circle_fragment_topic_mylin.setVisibility(View.VISIBLE);
            circle_fragment_topic_recy1.setLayoutManager(new LinearLayoutManager(getActivity()));
            cirFrTopicAdapter = new CirFrTopicAdapter(getActivity(), lisyMine,CirFrTopicAdapter.myCircle);
            cirFrTopicAdapter.setOnClick(CircleTopicFragment.this);
            circle_fragment_topic_recy1.setAdapter(cirFrTopicAdapter);
        }
    }


    private void setDataLunBo(CircleTopicBean circleTopicBean) {
        int[] arr=new int[]{R.drawable.dot_focuse,R.drawable.dot_normal};
        //设置图片的集合
        ArrayList<String> listImg=new ArrayList<>();
        for (int i = 0; i < circleTopicBean.getData().getBanner().size(); i++) {
            listImg.add(circleTopicBean.getData().getBanner().get(i).getImg());
        }
        //设置小圆点的集合
        ArrayList<ImageView> listDot=new ArrayList<>();
        for (int i = 0; i < circleTopicBean.getData().getBanner().size(); i++) {
            ImageView img=new ImageView(getActivity());
            if(i==0){
                img.setImageResource(R.drawable.dot_focuse);
            }else{
                img.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(15,15);
            params.setMargins(10,0,10,0);
            circle_fragment_topic_vp_lin.addView(img,params);
            listDot.add(img);
        }
        circle_fragment_topic_vp.initData(listImg,arr,listDot);
        circle_fragment_topic_vp.startViewPager();
        //设置监听
        circle_fragment_topic_vp.setOnPageClickListener(this);
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
        //我的圈子的布局
        circle_fragment_topic_mylin = (LinearLayout) vv.findViewById(R.id.circle_fragment_topic_mylin);
        //我的圈子
        circle_fragment_topic_recy1 = (RecyclerView) vv.findViewById(R.id.circle_fragment_topic_recy1);

        circle_fragment_topic_spring = (SpringView) vv.findViewById(R.id.circle_fragment_topic_spring);
        circle_fragment_topic_vp = (MyRoolViewPager) vv.findViewById(R.id.circle_fragment_topic_vp);
        //热门圈子
        circle_fragment_topic_recy = (RecyclerView) vv.findViewById(R.id.circle_fragment_topic_recy);
        circle_fragment_topic_vp_lin = (LinearLayout) vv.findViewById(R.id.circle_fragment_topic_vp_lin);
        //设置ScollView显示顶部
        circle_fragment_topic_recy.setFocusable(false);
    }
    //vp的点击监听
    @Override
    public void setOnPage(int position) {
        Toast.makeText(getActivity(), "..."+position, Toast.LENGTH_SHORT).show();
    }
    //recy的条目点击事件
   @Override
    public void setOnItemClick(int position) {
        //Toast.makeText(getActivity(), "条目点击..."+position, Toast.LENGTH_SHORT).show();
       //跳转到详情的界面
       Intent intent=new Intent(getActivity(), MyTopicDetail.class);
       intent.putExtra("nid",position);
       startActivity(intent);
    }
    //recy的条目中“加”图片的点击事件
    @Override
    public void setOnItemImgClick(int position) {
        lisyMine.add(circleTopicBean.getData().getCircle().get(position));
        setDataRecy(circleTopicBean);
        Toast.makeText(getActivity(), "图片点击..."+circleTopicBean.getData().getCircle().get(position).getN_title(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        circle_fragment_topic_spring.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        //让底部隐藏
        circle_fragment_topic_spring.setType(SpringView.Type.FOLLOW);
        circle_fragment_topic_spring.onFinishFreshAndLoad();
    }
}
