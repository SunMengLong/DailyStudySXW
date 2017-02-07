package com.explem.aidl.dailystudysxw.user.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.login_regist.LoginActivity;
import com.explem.aidl.dailystudysxw.login_regist.bean.CookieBack;
import com.explem.aidl.dailystudysxw.user.UserCourseActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.explem.aidl.dailystudysxw.view.SmoothCheckBox;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.R.attr.id;
import static android.R.id.text1;

/**
 * Created by Administrator on 2017/1/29 0029.
 */

public class Fragment_Mycourse extends Fragment {

    public String id;
    //正在下载
    @InjectView(R.id.frame_loading)View frame_loading;
    //内容
    @InjectView(R.id.framelayout_mycoursestuding)View framelayout_mycoursestuding;
    //没有要学的内容显示的界面
    @InjectView(R.id.framelayout_mycoursestuding_no)View framelayout_mycoursestuding_no;
    //listview
    @InjectView(R.id.listview_fragment_studing)SwipeMenuListView listview_fragment_studing;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycoursestuding, null);

        Bundle bundle = getArguments();
        id = bundle.getString("typeId", "非法ID");

        ButterKnife.inject(this,view);
        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView iv_common_header_manage = (TextView) getActivity().findViewById(R.id.iv_common_header_manage);
        iv_common_header_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.showToastA(getActivity(),"hahahahaha");
            }
        });
        showFragment();
    }

    public void showFragment(){
        if ("0".equals(id)){
//            LogUtils.showToastA(getActivity(),"已学完");
            frame_loading.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "1");
            map.put("p", "1");
            MyBaseData myBaseData = new MyBaseData();
            myBaseData.getDate(true, false, "http://www.meirixue.com", "http://www.meirixue.com/api.php?c=user&a=usercourse", 0, 0, BaseDate.postData, map);


        }else if ("1".equals(id)){
            LogUtils.showToastA(getActivity(),"正在学");
            frame_loading.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "2");
            map.put("p", "1");
            MyBaseData myBaseData = new MyBaseData();
            myBaseData.getDate(true, false, "http://www.meirixue.com", "http://www.meirixue.com/api.php?c=user&a=usercourse", 0, 0, BaseDate.postData, map);
        }
    }

    public Fragment getFragment(String typeId){
        Fragment_Mycourse fragment = new Fragment_Mycourse();
        Bundle bundle = new Bundle();
        bundle.putString("typeId", typeId);
        fragment.setArguments(bundle);
        return  fragment;
    }

    class MyBaseData extends BaseDate {
        @Override
        protected void setResultError(ShowingPage.StateType stateLoadError) {
        }

        @Override
        public void setResultData(String data) {
            Gson gson = new Gson();
            final CookieBack cookieBack = gson.fromJson(data, CookieBack.class);

            Message message = Message.obtain();
            message.obj = cookieBack;
            message.what = 0;
            handler.sendMessage(message);
        }
    }

    private int dp2px(int value) {
        // 第一个参数为我们待转的数据的单位，此处为 dp（dip）
        // 第二个参数为我们待转的数据的值的大小
        // 第三个参数为此次转换使用的显示量度（Metrics），它提供屏幕显示密度（density）和缩放信息
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }
    //另一种将dp转换为px的方法
    private int dp2px(float value){
        final float scale = getResources().getDisplayMetrics().density;
        return (int)(value*scale + 0.5f);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){

                SwipeMenuCreator creator = new SwipeMenuCreator() {

                    @Override
                    public void create(SwipeMenu menu) {
                        // 创建“删除”项
                        SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                        deleteItem.setBackground(new ColorDrawable(Color.rgb(249, 93, 81)));
                        deleteItem.setWidth(dp2px(90));
                        deleteItem.setTitle("删除");
                        deleteItem.setTitleSize(18);
                        deleteItem.setTitleColor(Color.WHITE);
                        // 将创建的菜单项添加进菜单中
                        menu.addMenuItem(deleteItem);
                    }
                };

                CookieBack cookieBack = (CookieBack) msg.obj;
                int status = cookieBack.getStatus();
                if (status == 205) {//未登录 跳转到登录界面
                    Toast.makeText(getActivity(), "未登录", Toast.LENGTH_SHORT).show();
                    JumpUtils.jumpOnly(getActivity(), LoginActivity.class);
                    return;
                }
                String count = cookieBack.getCount();
                if ("0".equals(count)){
                    frame_loading.setVisibility(View.GONE);
                    framelayout_mycoursestuding.setVisibility(View.GONE);
                    framelayout_mycoursestuding_no.setVisibility(View.VISIBLE);
                    return;
                }
                final List<CookieBack.DataBean> dataBeen = cookieBack.getData();

                final BaseAdapter adapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return dataBeen.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return dataBeen.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = View.inflate(getActivity(), R.layout.mycoursestuding_listview_item, null);
                        TextView textview_mycourse_title = (TextView) view.findViewById(R.id.textview_mycourse_title);
                        TextView textview_mycourse_price = (TextView) view.findViewById(R.id.textview_mycourse_price);
                        TextView tv_mycourse_progress = (TextView) view.findViewById(R.id.tv_mycourse_progress);
                        ProgressBar progressbar_mycourse = (ProgressBar) view.findViewById(R.id.progressbar_mycourse);
                        ImageView imageview_mycourse = (ImageView) view.findViewById(R.id.imageview_mycourse);
                        SmoothCheckBox scx_mycourse_delete = (SmoothCheckBox) view.findViewById(R.id.scx_mycourse_delete);

                        textview_mycourse_title.setText(dataBeen.get(position).getCourse_name());
                        textview_mycourse_price.setText("0.00".equals(dataBeen.get(position).getCourse_price()) ? "免费" : "$" + dataBeen.get(position).getCourse_price());
                        tv_mycourse_progress.setText(dataBeen.get(position).getStudycount() + "/" + dataBeen.get(position).getCourse_hour());
                        progressbar_mycourse.setMax(Integer.parseInt(dataBeen.get(position).getCourse_hour()));
                        progressbar_mycourse.setProgress(Integer.parseInt(dataBeen.get(position).getStudycount()));
                        Glide.with(getActivity()).load(dataBeen.get(position).getCourse_pic()).crossFade().into(imageview_mycourse);
                        return view;
                    }
                };
                listview_fragment_studing.setAdapter(adapter);
                listview_fragment_studing.setMenuCreator(creator);
                listview_fragment_studing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                        dataBeen.remove(position);
                        String cid = dataBeen.get(position).getCid();
                        LogUtils.showToastA(getActivity(),cid);
//                        adapter.notifyDataSetChanged();
                        return false;
                    }
                });
                frame_loading.setVisibility(View.GONE);
            }
        }
    };
}
