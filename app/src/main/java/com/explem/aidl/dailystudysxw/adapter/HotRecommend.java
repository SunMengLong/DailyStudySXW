package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.bean.Home_Fragment_main;

import java.util.List;

/**
 * Created by asus on 2017/1/12.
 */

public class HotRecommend extends BaseAdapter {
    private final Context context;
    private final List<Home_Fragment_main.DataBean.HotcourseBean> list;

    public HotRecommend(Context context, List<Home_Fragment_main.DataBean.HotcourseBean> list) {
    this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.hot_recommend_gv_item, null);
        ImageView hot_recommend_img = (ImageView) v.findViewById(R.id.hot_recommend_img);
        TextView hot_recommend_tv = (TextView) v.findViewById(R.id.hot_recommend_tv);
        TextView hot_recommend_tv2 = (TextView) v.findViewById(R.id.hot_recommend_tv2);
        hot_recommend_img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context) .load(list.get(i).getImg()) .into(hot_recommend_img);
        hot_recommend_tv.setText(list.get(i).getTitle());
        hot_recommend_tv2.setText(list.get(i).getName());
        return v;
    }
}
