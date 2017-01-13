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

public class MyGrideAdapter extends BaseAdapter {
    private final Context context;
    private final List<Home_Fragment_main.DataBean.HotcategoryBean> list;

    public MyGrideAdapter(Context context, List<Home_Fragment_main.DataBean.HotcategoryBean> list) {
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
        View v = View.inflate(context, R.layout.home_fragment_gridview_item, null);
        TextView home_fragment_gv_item_tv = (TextView) v.findViewById(R.id.home_fragment_gv_item_tv);
        ImageView home_fragment_gv_item_img = (ImageView) v.findViewById(R.id.home_fragment_gv_item_img);
        home_fragment_gv_item_tv.setText(list.get(i).getCname());
        Glide.with(context) .load(list.get(i).getImg()) .into(home_fragment_gv_item_img);




        return v;
    }
}
