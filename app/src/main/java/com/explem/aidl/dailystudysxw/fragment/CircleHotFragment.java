package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.application.MyApplication;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.bean.CircleHotTitleBean;
import com.explem.aidl.dailystudysxw.http.httpBoolean;
import com.explem.aidl.dailystudysxw.interfaces.ReShowingListener;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.CirclrURL;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Pooh on 2017/1/12.
 */

public class CircleHotFragment extends BaseFragment{
    //网络判断的boolean类型
    boolean isshow=true;
    private View vv;
    private ViewPager cir_fra_hot_vp;
    //标记
    //热门界面的title数据
    int title=0;
    int neirong=1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收传来的数据
            int arg1=msg.arg1;
            String data= (String) msg.obj;
            if(arg1==title){
                //解析数据
                Gson gson=new Gson();
                circleHotTitleBean = gson.fromJson(data, CircleHotTitleBean.class);
                //设置头部
                setTitleData(circleHotTitleBean);
                //vp的适配器
                setAdapterMy(circleHotTitleBean);
            }
        }
    };

    private void setAdapterMy(final CircleHotTitleBean circleHotTitleBean) {
        //vp的适配器
        cir_fra_hot_vp.setFocusable(true);
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MyFragment.getFragment(circleHotTitleBean.getData().get(position).getTid(),MyFragment.hotDetail,1);
            }

            @Override
            public int getCount() {
                return circleHotTitleBean.getData().size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return circleHotTitleBean.getData().get(position).getName();
            }
        };
        cir_fra_hot_vp.setAdapter(fragmentPagerAdapter);
        cir_fra_hot_tabLayout.setTabsFromPagerAdapter(fragmentPagerAdapter);
        //与vp进行关联
        cir_fra_hot_tabLayout.setupWithViewPager(cir_fra_hot_vp);

    }

    private void setTitleData(CircleHotTitleBean circleHotTitleBean) {
        cir_fra_hot_tabLayout.addTab(cir_fra_hot_tabLayout.newTab().setText(circleHotTitleBean.getData().get(0).getName()), true);
        //判断集合是否为空
        if(MyApplication.listTitle!=null&&MyApplication.listTitle.size()>0){
            MyApplication.listTitle.clear();
        }
        MyApplication.listTitle.add(circleHotTitleBean.getData().get(0).getName());
        for (int i = 1; i < circleHotTitleBean.getData().size(); i++) {
            cir_fra_hot_tabLayout.addTab(cir_fra_hot_tabLayout.newTab().setText(circleHotTitleBean.getData().get(i).getName()), false);
            MyApplication.listTitle.add(circleHotTitleBean.getData().get(i).getName());
        }
        cir_fra_hot_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                cir_fra_hot_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private TabLayout cir_fra_hot_tabLayout;
    private CircleHotTitleBean circleHotTitleBean;

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
            CircleHotFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }else {
            //设置为成功界面
            CircleHotFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        //对showingpager作出监听
        showingPage.setShowingPagerListener(new ReShowingListener() {
            @Override
            public void MyReshowingListener(View v) {
                //连接网络时，将状态值设为true,这里的网络判断已经在回调方法中判断
                isshow=true;
                //设置为成功界面
                CircleHotFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        });
        //判断网络是否连接
        if(isshow){
            CircleHotFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

    @Override
    protected View createSuccessView() {
        vv = View.inflate(getActivity(), R.layout.cir_fra_hot_view,null);
        //初始化控件
        initView();
        //设置数据
        setData();
        return vv;
    }

    private void setData() {
        //头部请求网络
        getDataFromNet(title,true,true,CirclrURL.circle_hot_baseUrl,CirclrURL.circle_hot_title_url,0,BaseDate.NOMALTIME,BaseDate.getData,null);

    }

    private void getDataFromNet(final int tag, boolean isReadCookie, boolean isSaveCookie, String path, String args, int index, int validTime, int type, final HashMap<String,String> map) {
        BaseDate baseData=new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                //传递给handler
                Message msg=new Message();
                msg.obj=data;
                msg.arg1=tag;
                handler.sendMessage(msg);
            }
        };
        baseData.getDate(isReadCookie,isSaveCookie,path,args,index,validTime,type,map);
    }

    private void initView() {
        cir_fra_hot_vp = (ViewPager) vv.findViewById(R.id.cir_fra_hot_vp);
        cir_fra_hot_tabLayout = (TabLayout) vv.findViewById(R.id.cir_fra_hot_tabLayout);
    }
}
