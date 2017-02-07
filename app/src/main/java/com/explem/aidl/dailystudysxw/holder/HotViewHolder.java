package com.explem.aidl.dailystudysxw.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;

/**
 * Created by Pooh on 2017/1/16.
 */

public class HotViewHolder extends RecyclerView.ViewHolder{

    public ImageView cir_fra_hot_item_titleimg;
    public ImageView cir_fra_hot_item_guanzhuimg;
    public ImageView cir_fra_hot_item_moreimg_img1;
    public ImageView cir_fra_hot_item_moreimg_img2;
    public ImageView cir_fra_hot_item_moreimg_img3;
    public ImageView cir_fra_hot_item_twoimg_img1;
    public ImageView cir_fra_hot_item_twoimg_img2;
    public ImageView cir_fra_hot_item_oneimg_img1;
    public LinearLayout cir_fra_hot_item_moreimg_lin;
    public LinearLayout cir_fra_hot_item_twoimg_lin;
    public LinearLayout cir_fra_hot_item_oneimg_lin;
    public TextView cir_fra_hot_item_titletv;
    public TextView cir_fra_hot_item_tv1;
    public TextView cir_fra_hot_item_tv2;
    public TextView cir_fra_hot_item_tv3;
    public TextView cir_fra_hot_item_tv4;
    public TextView cir_fra_hot_item_tv5;
    public TextView cir_fra_hot_item_tv6;

    public HotViewHolder(View itemView) {
        super(itemView);
        //图片
        cir_fra_hot_item_titleimg = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_titleimg);
        cir_fra_hot_item_guanzhuimg = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_guanzhuimg);
        cir_fra_hot_item_moreimg_img1 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_moreimg_img1);
        cir_fra_hot_item_moreimg_img2 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_moreimg_img2);
        cir_fra_hot_item_moreimg_img3 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_moreimg_img3);
        cir_fra_hot_item_twoimg_img1 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_twoimg_img1);
        cir_fra_hot_item_twoimg_img2 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_twoimg_img2);
        cir_fra_hot_item_oneimg_img1 = (ImageView) itemView.findViewById(R.id.cir_fra_hot_item_oneimg_img1);
        //linearLayout
        cir_fra_hot_item_moreimg_lin = (LinearLayout) itemView.findViewById(R.id.cir_fra_hot_item_moreimg_lin);
        cir_fra_hot_item_twoimg_lin = (LinearLayout) itemView.findViewById(R.id.cir_fra_hot_item_twoimg_lin);
        cir_fra_hot_item_oneimg_lin = (LinearLayout) itemView.findViewById(R.id.cir_fra_hot_item_oneimg_lin);
        //TextView
        cir_fra_hot_item_titletv = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_titletv);
        cir_fra_hot_item_tv1 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv1);
        cir_fra_hot_item_tv2 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv2);
        cir_fra_hot_item_tv3 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv3);
        cir_fra_hot_item_tv4 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv4);
        cir_fra_hot_item_tv5 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv5);
        cir_fra_hot_item_tv6 = (TextView) itemView.findViewById(R.id.cir_fra_hot_item_tv6);
    }
}
