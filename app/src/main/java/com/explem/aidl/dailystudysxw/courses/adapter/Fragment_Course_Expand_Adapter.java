package com.explem.aidl.dailystudysxw.courses.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.courses.bean.CourseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public class Fragment_Course_Expand_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CourseInfo> list;
    private List<Integer> imgList = new ArrayList<>();
    private List<Integer> ColorList = new ArrayList<>();
    private List<Integer> DownList = new ArrayList<>();
    private SparseBooleanArray selected;
    private int parentPosition = -1;
    private boolean isHide;

    public Fragment_Course_Expand_Adapter(Context context, List<CourseInfo> list) {
        this.context = context;
        this.list = list;
        selected = new SparseBooleanArray();
        initList(context);
    }

    private void initList(Context context) {
        imgList.add(R.mipmap.heart);
        imgList.add(R.mipmap.coffee);
        imgList.add(R.mipmap.diamond);
        imgList.add(R.mipmap.fourr);
        imgList.add(R.mipmap.hat);
        imgList.add(R.mipmap.language);

        ColorList.add(context.getResources().getColor(R.color.red));
        ColorList.add(context.getResources().getColor(R.color.orange));
        ColorList.add(context.getResources().getColor(R.color.green1));
        ColorList.add(context.getResources().getColor(R.color.red1));
        ColorList.add(context.getResources().getColor(R.color.blue1));
        ColorList.add(context.getResources().getColor(R.color.purple));

        DownList.add(R.mipmap.heart_s);
        DownList.add(R.mipmap.coffee_s);
        DownList.add(R.mipmap.diamond_s);
        DownList.add(R.mipmap.heart_s);
        DownList.add(R.mipmap.hat_s);
        DownList.add(R.mipmap.language_s);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition).getCname();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getNodes().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.sublayout_fragment_course_expand_group, null);

        TextView tv_sublayout_course_expand_group_title = (TextView) view.findViewById(R.id.tv_sublayout_course_expand_group_title);
        ImageView iv_sublayout_course_expand_group_icon = (ImageView) view.findViewById(R.id.iv_sublayout_course_expand_group_icon);
        ImageView iv_sublayout_course_expand_group_down = (ImageView) view.findViewById(R.id.iv_sublayout_course_expand_group_down);

        iv_sublayout_course_expand_group_icon.setImageResource(imgList.get(groupPosition));
        tv_sublayout_course_expand_group_title.setText(list.get(groupPosition).getCname());

        if (isHide == true && parentPosition == groupPosition){
            tv_sublayout_course_expand_group_title.setTextColor(ColorList.get(groupPosition));
            iv_sublayout_course_expand_group_down.setImageResource(DownList.get(groupPosition));
        }else {
            tv_sublayout_course_expand_group_title.setTextColor(context.getResources().getColor(R.color.black));
            iv_sublayout_course_expand_group_down.setImageResource(R.mipmap.down);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.sublayout_fragment_course_expand_child, parent, false);
        GridView gv_sublayout_course_expand_child = (GridView) view.findViewById(R.id.gv_sublayout_course_expand_child);
        gv_sublayout_course_expand_child.setAdapter(new Fragment_Course_Child_Gridview_Adapter(context,list.get(groupPosition).getNodes()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    //点击以后该条目是隐藏还是展开
    public void setHide(boolean isHide,int position) {
        this.parentPosition = position;
        this.isHide = isHide;
    }
}
