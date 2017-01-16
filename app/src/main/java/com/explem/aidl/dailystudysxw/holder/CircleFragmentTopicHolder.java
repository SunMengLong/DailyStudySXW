package com.explem.aidl.dailystudysxw.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.explem.aidl.dailystudysxw.R;

/**
 * Created by Pooh on 2017/1/13.
 */

public class CircleFragmentTopicHolder extends RecyclerView.ViewHolder{

    public final ImageView cir_fra_recy_item_titleimg;
    public final ImageView cir_fra_recy_item_addimg;
    public final ImageView cir_fra_recy_item_hotimg;
    public final TextView cir_fra_recy_item_tv1;
    public final TextView cir_fra_recy_item_tv2;
    public final TextView cir_fra_recy_item_tv3;
    public final TextView cir_fra_recy_item_tv4;

    public CircleFragmentTopicHolder(View itemView) {
        super(itemView);
        cir_fra_recy_item_titleimg = (ImageView) itemView.findViewById(R.id.cir_fra_recy_item_titleimg);
        cir_fra_recy_item_addimg = (ImageView) itemView.findViewById(R.id.cir_fra_recy_item_addimg);
        cir_fra_recy_item_hotimg = (ImageView) itemView.findViewById(R.id.cir_fra_recy_item_hotimg);
        cir_fra_recy_item_tv1 = (TextView) itemView.findViewById(R.id.cir_fra_recy_item_tv1);
        cir_fra_recy_item_tv2 = (TextView) itemView.findViewById(R.id.cir_fra_recy_item_tv2);
        cir_fra_recy_item_tv3 = (TextView) itemView.findViewById(R.id.cir_fra_recy_item_tv3);
        cir_fra_recy_item_tv4 = (TextView) itemView.findViewById(R.id.cir_fra_recy_item_tv4);

    }
}
