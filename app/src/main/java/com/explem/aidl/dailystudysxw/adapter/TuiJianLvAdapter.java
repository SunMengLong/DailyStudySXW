package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class TuiJianLvAdapter extends BaseAdapter {

    private final Context context;
    private final List<Home_Fragment_main.DataBean.IndexrecommendBean.ListviewBean> list;



    public TuiJianLvAdapter(Context context, List<Home_Fragment_main.DataBean.IndexrecommendBean.ListviewBean> list) {
        this.context = context;
        this.list = list;
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
        View v = View.inflate(context, R.layout.home_fragment_lv_item, null);
        ImageView tuijian_lv_img = (ImageView) v.findViewById(R.id.tuijian_lv_img);
        TextView tuijian_lv_name = (TextView) v.findViewById(R.id.tuijian_lv_name);
        TextView school_name = (TextView) v.findViewById(R.id.school_name);
        TextView user = (TextView) v.findViewById(R.id.user);
        TextView tuijian_lv_price = (TextView) v.findViewById(R.id.tuijian_lv_price);
        Glide.with(context).load(list.get(i).getCourse_pic()).into(tuijian_lv_img);
        tuijian_lv_name.setText(list.get(i).getCourse_name());
        school_name.setText(list.get(i).getSchool_name());
        String course_price = list.get(i).getCourse_price();
        if(!course_price.equals("0.00")){
            tuijian_lv_price.setText("￥"+list.get(i).getCourse_price());
            tuijian_lv_price.setTextColor(Color.RED);
        }else {
            tuijian_lv_price.setText("免费");
            tuijian_lv_price.setTextColor(Color.GREEN);
        }
        user.setText(list.get(i).getUsercount()+"");
        return v;
    }


}
