package com.explem.aidl.dailystudysxw.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.AdventureActivity;
import com.explem.aidl.dailystudysxw.bean.Home_Fragment_main;

import java.util.List;

/**
 * 主界面轮播图适配器
 * XuJiaJian
 */

public class MyLunBoAdapter extends PagerAdapter  {
    private final Context context;
    private final List<Home_Fragment_main.DataBean.SliderBean> list;
    private final Handler h;

    public MyLunBoAdapter(Context context, List<Home_Fragment_main.DataBean.SliderBean> list, Handler h) {
    this.context=context;
        this.list=list;
        this.h=h;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {
        ImageView img =new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context)  .load(list.get(position%list.size()).getImg()) .into(img);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_UP:
                                h.sendEmptyMessageDelayed(0,5000);
                                    Intent intent =new Intent(context, AdventureActivity.class);
                                    context.startActivity(intent);
                                break;
                            case  MotionEvent.ACTION_DOWN:
                                h.removeCallbacksAndMessages(null);
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                h.sendEmptyMessageDelayed(0, 5000);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                h.removeCallbacksAndMessages(null);
                                break;
                    }
                return true;
            }
        });
        container.addView(img);
        return img;
    }


}
