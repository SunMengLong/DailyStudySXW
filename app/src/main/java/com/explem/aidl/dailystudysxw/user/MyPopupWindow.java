package com.explem.aidl.dailystudysxw.user;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.utils.LogUtils;

/**
 * Created by Administrator on 2017/1/26 0026.
 */

public class MyPopupWindow extends PopupWindow {

    public final Button popup_button_camera;
    public final Button popup_button_album;

    public MyPopupWindow(Context context, View.OnClickListener onClickListener) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_window_change_photo, null);

        popup_button_camera = (Button) view.findViewById(R.id.popup_button_camera);
        popup_button_album = (Button) view.findViewById(R.id.popup_button_album);
        Button popup_button_concel = (Button) view.findViewById(R.id.popup_button_concel);

        popup_button_concel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        popup_button_camera.setOnClickListener(onClickListener);
        popup_button_album.setOnClickListener(onClickListener);

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.Animation);
        ColorDrawable colorDrawable = new ColorDrawable(0xa0000000);
        this.setBackgroundDrawable(colorDrawable);

    }
}
