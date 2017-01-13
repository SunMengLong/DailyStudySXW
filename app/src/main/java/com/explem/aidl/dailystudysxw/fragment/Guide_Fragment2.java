package com.explem.aidl.dailystudysxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.explem.aidl.dailystudysxw.R;

/**
 * Created by asus on 2017/1/11.
 * 导航页1
 * XuJiaJian
 */

public class Guide_Fragment2 extends Fragment {

    private ImageView guide_fragment_img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.guild_fragment2, null);
        guide_fragment_img = (ImageView) inflate.findViewById(R.id.Guide_fragment_img);

        return inflate;
    }
    @Override
    public void onStart() {
        super.onStart();
        guide_fragment_img.setImageResource(R.mipmap.guide2);
    }

}
