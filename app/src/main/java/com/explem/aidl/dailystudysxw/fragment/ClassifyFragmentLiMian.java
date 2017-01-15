package com.explem.aidl.dailystudysxw.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.base.BaseFragment;
import com.explem.aidl.dailystudysxw.courses.adapter.Fragment_Course_Expand_Adapter;
import com.explem.aidl.dailystudysxw.courses.bean.CourseInfo;
import com.explem.aidl.dailystudysxw.http.httpBoolean;
import com.explem.aidl.dailystudysxw.interfaces.ReShowingListener;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Pooh on 2017/1/12.
 */

public class ClassifyFragmentLiMian extends BaseFragment {
    //网络判断的boolean类型
    boolean isshow = true;
    String baseUrl = "http://www.meirixue.com";
    String    sort = "http://www.meirixue.com/api.php?c=category&a=getall";
    public ExpandableListView expand_fragment_course;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //第一次进入判断网络，如果未联网状态，则将状态值设为false
        if (httpBoolean.checkNetworkState(getActivity())) {
            isshow = false;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            List<CourseInfo> infos = (List<CourseInfo>) msg.obj;
//            LogUtils.i("course", "@@@@@@@@@@@@!!!!"+infos.size());
            final Fragment_Course_Expand_Adapter adapter = new Fragment_Course_Expand_Adapter(getActivity(), infos);
            expand_fragment_course.setGroupIndicator(null);
            expand_fragment_course.setAdapter(adapter);
            expand_fragment_course.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                adapter.setSelectedItem(groupPosition);
                    if (!parent.isGroupExpanded(groupPosition)) {
                        adapter.setHide(true, groupPosition);
                    } else {
                        adapter.setHide(false, groupPosition);
                    }
                    return false;
                }
            });
            //同时只有一个group是打开状态
            expand_fragment_course.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        if (groupPosition != i) {
                            expand_fragment_course.collapseGroup(i);
                        }
                    }
                }
            });
        }
    };

    @Override
    protected void onload() {
        //判断网络是否连接
        if (httpBoolean.checkNetworkState(getActivity())) {

            ClassifyFragmentLiMian.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        } else {
            //设置为成功界面
            LogUtils.i("course", "连接网络442");

            CourseBaseData baseData = new CourseBaseData();
            baseData.getDate(true,true,baseUrl, sort, 0, BaseDate.NOTIME, BaseDate.getData, null);

//            ClassifyFragmentLiMian.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
        //对showingpager作出监听
        showingPage.setShowingPagerListener(new ReShowingListener() {
            @Override
            public void MyReshowingListener(View v) {
                //连接网络时，将状态值设为true,这里的网络判断已经在回调方法中判断
                isshow = true;
                //设置为成功界面
                ClassifyFragmentLiMian.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        });
        //判断网络是否连接
        if (isshow) {
            ClassifyFragmentLiMian.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

    @Override
    protected View createSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_course, null);

        expand_fragment_course = (ExpandableListView) view.findViewById(R.id.expand_fragment_course);

        return view;
    }

    class CourseBaseData extends BaseDate {
        @Override
        protected void setResultError(ShowingPage.StateType stateLoadError) {
        }

        @Override
        public void setResultData(String data) {
            Gson gson = new Gson();
            List<CourseInfo> courseInfo = gson.fromJson(data, new TypeToken<List<CourseInfo>>() {
            }.getType());

            Message message = handler.obtainMessage();
            message.obj = courseInfo;
            message.sendToTarget();
//            LogUtils.i("course", courseInfo.size()+"@@@@@@@@@@@@");
            ClassifyFragmentLiMian.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

}
