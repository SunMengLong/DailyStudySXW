package com.explem.aidl.dailystudysxw.courses.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.courses.bean.CourseInfo;
import com.explem.aidl.dailystudysxw.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public class Fragment_Course_Child_Gridview_Adapter extends BaseAdapter {
    private final List<CourseInfo.NodesBean> list;
    private final Context context;

    public Fragment_Course_Child_Gridview_Adapter(Context context, List<CourseInfo.NodesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if ("1".equals(list.get(0).getCategory_fiid())) {
            return list.size();
        }
        return list.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_gridview, null);
        TextView bt_gridview = (TextView) convertView.findViewById(R.id.bt_gridview);
        if ("1".equals(list.get(0).getCategory_fiid())) {
//            LogUtils.i("adapter",list.size()+"@@@"+position);
            bt_gridview.setText(list.get(position).getCategory_name());
        } else {
            if (position == 0) {
                bt_gridview.setText("全部");
            } else {
                bt_gridview.setText(list.get(position - 1).getCategory_name());
            }
        }
        return convertView;
    }
}
