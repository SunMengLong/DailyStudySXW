package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.application.MyApplication;
import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.adapter.DirectorAdapter;
import com.explem.aidl.dailystudysxw.adapter.EveryBodyAdapter;
import com.explem.aidl.dailystudysxw.adapter.HotRecommend;
import com.explem.aidl.dailystudysxw.adapter.MyGrideAdapter;
import com.explem.aidl.dailystudysxw.adapter.MyLunBoAdapter;
import com.explem.aidl.dailystudysxw.adapter.TuiJianLvAdapter;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.bean.Home_Fragment_main;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.view.MyGridView;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pooh on 2017/1/10.
 */

public class Home_Fragment extends BaseFragment implements SpringView.OnFreshListener {
    private PopupWindow pop;
    private TextView tv;
    private int main_tag = 0;
    private String main_path = "http://www.meirixue.com";
    private String main_args = "http://www.meirixue.com/api.php?a=indexv9&c=index";
    private View popView;
    private View v;
    private ViewPager home_fragment_lunbo;
    private Home_Fragment_main home_fragment_main;
    private LinearLayout ll_doc;
    private ArrayList<ImageView> docList;
    private MyGridView home_fragment_gv;
    private TextView home_fragment_silu;
    private TextView home_fragment_siwei;
    private ImageView home_fragment_head;
    private TextView run;
    private TextView wondeful;
    private ImageView run_img;
    private TextView siwei;
    private TextView open_heand;
    private ImageView bulb;
    private MyGridView hot_gv;
    private MyGridView director_gv;
    private GridView tuijian_lv;
    private GridView everyonestudy;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int width = pop.getHeight();
            int height = pop.getWidth();
            pop.showAtLocation(popView, Gravity.LEFT | Gravity.TOP, v.getWidth() / 2 - width / 2, v.getHeight() / 2 - height / 2);
        }
    };
    Handler h =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //自动轮播
            int currentItem = home_fragment_lunbo.getCurrentItem();
            currentItem++;
            home_fragment_lunbo.setCurrentItem(currentItem);
            sendEmptyMessageDelayed(0, 5000);
        }
    };
    //解析数据
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == main_tag) {
                home_fragment_main = (Home_Fragment_main) msg.obj;
                home_fragment_lunbo.setAdapter(new MyLunBoAdapter(getActivity(), home_fragment_main.getData().getSlider(),h));
                home_fragment_lunbo.setCurrentItem(3000);
                getDoc();
                //圆点的监听
                home_fragment_lunbo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }
                    @Override
                    public void onPageSelected(int position) {
                        for (int i = 0; i < docList.size(); i++) {
                            if (position%docList.size() == i) {
                                docList.get(i).setImageResource(R.drawable.dot_focuse);
                            }else {
                                docList.get(i).setImageResource(R.drawable.dot_normal);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                h.sendEmptyMessageDelayed(0, 5000);
                ArrayList<Home_Fragment_main.DataBean.HotcategoryBean> homeList =new ArrayList<>();
                //判断下表
                    for (int i = 0; i < 6; i++) {
                        homeList.add(home_fragment_main.getData().getHotcategory().get(i));
                }
                //Gride适配器
                home_fragment_gv.setAdapter(new MyGrideAdapter(getActivity(),homeList));
                //最强思路
                Thinking();
                //热门推荐
                hot_gv.setAdapter(new HotRecommend(getActivity(),home_fragment_main.getData().getHotcourse()));
                director_gv.setAdapter(new DirectorAdapter(getActivity(),home_fragment_main.getData().getIndexrecommend().getTop()));
                tuijian_lv.setAdapter(new TuiJianLvAdapter(getActivity(),home_fragment_main.getData().getIndexrecommend().getListview()));
                everyonestudy.setAdapter(new EveryBodyAdapter(getActivity(),home_fragment_main.getData().getIndexothers()));
            }
        }
        //最强思路
        private void Thinking() {
            //最强思路
            home_fragment_silu.setText(home_fragment_main.getData().getAdlist().get(0).getName());
            home_fragment_siwei.setText(home_fragment_main.getData().getAdlist().get(0).getTitle());
            //加载正常图片
            Glide.with(getActivity()) .load(home_fragment_main.getData().getAdlist().get(0).getImg()) .into(home_fragment_head);
            run.setText(home_fragment_main.getData().getAdlist().get(1).getName());
            wondeful.setText(home_fragment_main.getData().getAdlist().get(1).getTitle());
            Glide.with(getActivity()) .load(home_fragment_main.getData().getAdlist().get(1).getImg()) .into(run_img);
            siwei.setText(home_fragment_main.getData().getAdlist().get(2).getName());
            open_heand.setText(home_fragment_main.getData().getAdlist().get(2).getTitle());
            Glide.with(getActivity()) .load(home_fragment_main.getData().getAdlist().get(2).getImg()) .into(bulb);
        }
    };


    @Override
    protected void onload() {
        //设置为成功界面
        Home_Fragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    //请求成功加载布局
    @Override
    protected View createSuccessView() {
        //判断“圈子”界面将要显示的是哪一个界面
        MyApplication.direction2=-1;

         TextView tv=new TextView(getActivity());
        v = View.inflate(getActivity(), R.layout.home_fragment, null);
        initView();
        return v;
    }

    private void initView() {
        SpringView home_fragment_spring = (SpringView) v.findViewById(R.id.home_fragment_spring);
        home_fragment_lunbo = (ViewPager) v.findViewById(R.id.home_fragment_lunbo);
        ll_doc = (LinearLayout) v.findViewById(R.id.ll_doc);
        home_fragment_gv = (MyGridView) v.findViewById(R.id.home_fragment_gv);
        home_fragment_silu = (TextView) v.findViewById(R.id.home_fragment_silu);
        home_fragment_siwei = (TextView) v.findViewById(R.id.home_fragment_siwei);
        home_fragment_head = (ImageView) v.findViewById(R.id.home_fragment_head);
        run = (TextView) v.findViewById(R.id.run);
        run_img = (ImageView) v.findViewById(R.id.run_img);
        wondeful = (TextView) v.findViewById(R.id.wondeful);
        siwei = (TextView) v.findViewById(R.id.siwei);
        open_heand = (TextView) v.findViewById(R.id.open_heand);
        bulb = (ImageView) v.findViewById(R.id.bulb);
        hot_gv = (MyGridView) v.findViewById(R.id.hot_gv);
        director_gv = (MyGridView) v.findViewById(R.id.director_gv);
        tuijian_lv = (GridView) v.findViewById(R.id.tuijian_lv);
        //大家都在学的条目
        everyonestudy = (GridView) v.findViewById(R.id.everyonestudy);
        SharedPreferences sp = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        //第一次登录
        FirstLogin(sp, flag);
        getNet(main_path, main_args, main_tag, BaseDate.NOTIME, BaseDate.getData, null);
        //SpringView添加头部
        home_fragment_spring.setHeader(new DefaultHeader(getActivity()));
        home_fragment_spring.setType(SpringView.Type.FOLLOW);
        home_fragment_spring.setListener(this);
    }

    //得到小圆点
    private void getDoc() {
        docList = new ArrayList<>();
        for (int i = 0; i < home_fragment_main.getData().getSlider().size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i==0) {
                imageView.setImageResource(R.drawable.dot_focuse);
            }else {
                imageView.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,15);
            params.setMargins(10, 0, 10, 0);
            ll_doc.addView(imageView,params);
            docList.add(imageView);
        }
    }


    //网络请求
    public void getNet(String path, String args, final int index, int time, int type, HashMap<String, String> map) {
        new BaseDate() {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                home_fragment_main = gson.fromJson(data, Home_Fragment_main.class);
                Message msg = Message.obtain();
                msg.obj = home_fragment_main;
                msg.arg1 = index;
                mHandler.sendMessage(msg);
            }
        }.getDate(true ,true,path, args, 1, BaseDate.NOTIME, BaseDate.getData, null);
    }

    //第一次运行
    private void FirstLogin(SharedPreferences sp, boolean flag) {
        //第一次运行
        if (!flag) {
            getPop();
        } else {
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("flag", true);
        edit.commit();
    }

    //第一次登录提示图片
    private void getPop() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                popView = View.inflate(getActivity(), R.layout.home_fragment_pop, null);
                pop = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                pop.setOutsideTouchable(true);
                pop.setBackgroundDrawable(new BitmapDrawable());
                //使pop隐藏
                popView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                handler.obtainMessage(0).sendToTarget();
            }
        }.start();

    }

    //下拉刷新
    @Override
    public void onRefresh() {

    }

    //上拉加载
    @Override
    public void onLoadmore() {
//
    }
    //处理轮播图
    @Override
    public void onPause() {
        super.onPause();
       h.removeCallbacksAndMessages(null);
    }
}
