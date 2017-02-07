package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.bean.CircleTopicBean;
import com.explem.aidl.dailystudysxw.holder.CircleFragmentTopicHolder;
import com.explem.aidl.dailystudysxw.interfaces.CirTopicOnClick;

import java.util.ArrayList;

/**
 * Created by Pooh on 2017/1/13.
 */

public class CirFrTopicAdapter extends RecyclerView.Adapter<CircleFragmentTopicHolder>{
    Context context;
    ArrayList<CircleTopicBean.DataBean.CircleBean> list;
    private CirTopicOnClick cirTopicOnClick;
    private View vv;
    private int type;

    //我的圈子
    public static int myCircle=0;
    public static int hotCircle=1;

    public CirFrTopicAdapter(Context context, ArrayList<CircleTopicBean.DataBean.CircleBean> list,int type) {
        this.context = context;
        this.list = list;
        this.type=type;
    }

    @Override
    public CircleFragmentTopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        vv = View.inflate(context, R.layout.cir_fra_recy_item,null);
        CircleFragmentTopicHolder holder=new CircleFragmentTopicHolder(vv);

        return holder;
    }

    @Override
    public void onBindViewHolder(CircleFragmentTopicHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getN_small_img()).into(holder.cir_fra_recy_item_titleimg);
        holder.cir_fra_recy_item_tv1.setText(list.get(position).getN_title());
        holder.cir_fra_recy_item_tv2.setText(list.get(position).getN_brief());
        holder.cir_fra_recy_item_tv3.setText(list.get(position).getN_user_count()+"人关注");
        holder.cir_fra_recy_item_tv4.setText(list.get(position).getN_post_count()+"帖子");
        if(type==myCircle){
            holder.cir_fra_recy_item_addimg.setImageResource(R.mipmap.myrig);
        }
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cirTopicOnClick.setOnItemClick(Integer.parseInt(list.get(position).getNid()));
            }
        });
        holder.cir_fra_recy_item_addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cirTopicOnClick.setOnItemImgClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //点击事件的方法
    public void setOnClick(CirTopicOnClick cirTopicOnClick){
        this.cirTopicOnClick=cirTopicOnClick;
    }
}
