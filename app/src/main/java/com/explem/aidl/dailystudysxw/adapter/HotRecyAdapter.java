package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.bean.HotBean;
import com.explem.aidl.dailystudysxw.holder.HotViewHolder;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Pooh on 2017/1/16.
 */

public class HotRecyAdapter extends RecyclerView.Adapter<HotViewHolder>{
    public Context context;
    public List<HotBean.DataBean> list;
    private String[] imgArr=null;

    public HotRecyAdapter(ArrayList<HotBean.DataBean> list, Context context) {
        this.list=list;
        this.context=context;
    }
    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cir_fra_hot_item,null);
        HotViewHolder viewHolder=new HotViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        //赋值,将数组置为空
        imgArr=null;
        //解析图片的数据
        if(list.get(position).getSource()!=null) {
            String sourse = list.get(position).getSource();
            String sourse2 = sourse.substring(1, sourse.length() - 1);
            String[] arr = sourse2.split(",");
            imgArr = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {
                String arrmy=arr[i].substring(1,arr[i].length()-1);
                imgArr[i] = "http://img.dianfu.net/img/"+arrmy.substring(arrmy.length()-30,arrmy.length()-22)+"/"+arrmy.substring(arrmy.length()-20,arrmy.length());
            }
        }
            //判断数组的长度，要显示几张图片
            if (imgArr != null) {
                if (imgArr.length == 1) {
                    //显示一张
                    holder.cir_fra_hot_item_twoimg_lin.setVisibility(View.GONE);
                    holder.cir_fra_hot_item_oneimg_lin.setVisibility(View.VISIBLE);
                    holder.cir_fra_hot_item_moreimg_lin.setVisibility(View.GONE);
                    //设置图片资源
                    holder.cir_fra_hot_item_oneimg_img1.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context).load(imgArr[0]).into(holder.cir_fra_hot_item_oneimg_img1);
                } else if (imgArr.length == 2) {
                    //显示二张
                    holder.cir_fra_hot_item_twoimg_lin.setVisibility(View.VISIBLE);
                    holder.cir_fra_hot_item_oneimg_lin.setVisibility(View.GONE);
                    holder.cir_fra_hot_item_moreimg_lin.setVisibility(View.GONE);
                    //设置图片资源
                    holder.cir_fra_hot_item_twoimg_img1.setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.cir_fra_hot_item_twoimg_img2.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context).load(imgArr[0]).into(holder.cir_fra_hot_item_twoimg_img1);
                    Glide.with(context).load(imgArr[1]).into(holder.cir_fra_hot_item_twoimg_img2);
                } else if (imgArr.length >= 3) {
                    //显示三张
                    holder.cir_fra_hot_item_twoimg_lin.setVisibility(View.GONE);
                    holder.cir_fra_hot_item_oneimg_lin.setVisibility(View.GONE);
                    holder.cir_fra_hot_item_moreimg_lin.setVisibility(View.VISIBLE);
                    //设置图片资源
                    holder.cir_fra_hot_item_moreimg_img1.setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.cir_fra_hot_item_moreimg_img2.setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.cir_fra_hot_item_moreimg_img3.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context).load(imgArr[0]).into(holder.cir_fra_hot_item_moreimg_img1);

                    //设置头像
                    //Glide.with(context).load(list.get(position).getUser_small_log()).bitmapTransform(new BlurTransformation(context, 20), new CropCircleTransformation(context)).into(holder.cir_fra_hot_item_moreimg_img1);
                    Glide.with(context).load(imgArr[1]).into(holder.cir_fra_hot_item_moreimg_img2);
                    Glide.with(context).load(imgArr[2]).into(holder.cir_fra_hot_item_moreimg_img3);
                }
            } else {
                //显示0张
                holder.cir_fra_hot_item_twoimg_lin.setVisibility(View.GONE);
                holder.cir_fra_hot_item_oneimg_lin.setVisibility(View.GONE);
                holder.cir_fra_hot_item_moreimg_lin.setVisibility(View.GONE);
            }

        //设置头像
        Glide.with(context).load(list.get(position).getUser_small_log()).bitmapTransform(new BlurTransformation(context, 20), new CropCircleTransformation(context)).into(holder.cir_fra_hot_item_titleimg);
        //设置TextView数据
        holder.cir_fra_hot_item_titletv.setText(" "+list.get(position).getUser_name());
        holder.cir_fra_hot_item_tv3.setText("#"+ "星座"+"#");
        if(TextUtils.isEmpty(list.get(position).getP_leaderette())){
            holder.cir_fra_hot_item_tv2.setVisibility(View.GONE);
        }else {
            holder.cir_fra_hot_item_tv2.setText(list.get(position).getP_leaderette());
        }
        if(TextUtils.isEmpty(list.get(position).getP_title())){
            holder.cir_fra_hot_item_tv1.setVisibility(View.GONE);
        }else {
            holder.cir_fra_hot_item_tv1.setText(list.get(position).getP_title());
        }
        holder.cir_fra_hot_item_tv4.setText(list.get(position).getP_collectcount()+"");
        holder.cir_fra_hot_item_tv5.setText(list.get(position).getP_replycount()+"");
        holder.cir_fra_hot_item_tv6.setText(list.get(position).getP_sharecount()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
