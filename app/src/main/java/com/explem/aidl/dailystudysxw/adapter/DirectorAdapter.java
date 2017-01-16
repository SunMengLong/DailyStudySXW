package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.bean.Home_Fragment_main;

import java.util.List;

/**
 * 小编推荐
 * XuJiaJian
 * Created by asus on 2017/1/12.
 */

public class DirectorAdapter extends BaseAdapter {
    private final Context context;
    private final List<Home_Fragment_main.DataBean.IndexrecommendBean.TopBean> list;

    public DirectorAdapter(Context context, List<Home_Fragment_main.DataBean.IndexrecommendBean.TopBean> list) {
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
        View v = View.inflate(context, R.layout.homefragment_director_gv_item, null);
        ImageView director_gv_img = (ImageView) v.findViewById(R.id.director_gv_img);
        director_gv_img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context) .load(list.get(i).getCourse_pic()) .into(director_gv_img);
        return v;
    }
}
