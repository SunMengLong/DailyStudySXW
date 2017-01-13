package com.explem.aidl.dailystudysxw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.explem.aidl.dailystudysxw.MainActivity;
import com.explem.aidl.dailystudysxw.R;

/**
 * Created by asus on 2017/1/11.
 * 导航页1
 * XuJiaJian
 */

public class Guide_Fragment3 extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.guild_fragment3, null);
        ImageView Guide_fragment_img2 = (ImageView) v.findViewById(R.id.Guide_fragment_img2);
        Guide_fragment_img2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Intent in =new Intent(getActivity(), MainActivity.class);
        startActivity(in);
        getActivity().finish();
    }
}
