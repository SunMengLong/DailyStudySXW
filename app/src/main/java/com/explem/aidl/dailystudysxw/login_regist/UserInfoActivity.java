package com.explem.aidl.dailystudysxw.login_regist;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.explem.aidl.dailystudysxw.MainActivity;
import com.explem.aidl.dailystudysxw.R;
import com.explem.aidl.dailystudysxw.cookie.SharedPreferencesUtils;
import com.explem.aidl.dailystudysxw.fragment.Mine_Fragment;
import com.explem.aidl.dailystudysxw.login_regist.bean.CookieBack;
import com.explem.aidl.dailystudysxw.user.MyPopupWindow;
import com.explem.aidl.dailystudysxw.user.UserCourseActivity;
import com.explem.aidl.dailystudysxw.utils.BaseDate;
import com.explem.aidl.dailystudysxw.utils.JumpUtils;
import com.explem.aidl.dailystudysxw.utils.LogUtils;
import com.explem.aidl.dailystudysxw.view.ShowingPage;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.client.CookieStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;
import static com.explem.aidl.dailystudysxw.R.id.civ_fragment_mine_icon;

public class UserInfoActivity extends AppCompatActivity {

    @InjectView(R.id.tv_common_header_title)
    TextView tv_common_header_title;

    @InjectView(R.id.civ_user_icon)
    CircleImageView civ_user_icon;

    @InjectView(R.id.tv_activity_user_name)
    TextView tv_activity_user_name;

    @InjectView(R.id.tv_activity_user_phone)
    TextView tv_activity_user_phone;
    public PopupWindow myPopupWindow;
    public File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.inject(this);

        tv_common_header_title.setText("个人信息");

        file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        String img = SharedPreferencesUtils.getString(this, "img", "img");
        if ("img".equals(img)) {
            Bitmap bitmap = BitmapFactory.decodeFile(img);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            civ_user_icon.setImageBitmap(bitmap);
        }

        tv_activity_user_name.setText(SharedPreferencesUtils.getString(this, "name", "请设置姓名"));
        tv_activity_user_phone.setText(SharedPreferencesUtils.getString(this, "phone", "请设置电话"));

    }

    @OnClick(R.id.my_info_exit)
    public void exit() {
        SharedPreferencesUtils.saveBoolean(this, "isLogedin", false);
        finish();
    }

    @OnClick(R.id.iv_common_header_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.arl_user_seticon)
    public void setIcon() {
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        attr.alpha = 0.5f;
        getWindow().setAttributes(attr);
        myPopupWindow = new MyPopupWindow(this, onClickListener);
        myPopupWindow.showAtLocation(this.findViewById(R.id.activity_user_info), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        myPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.popup_button_camera://调用相机
                    myPopupWindow.dismiss();
                    LogUtils.showToastA(UserInfoActivity.this, "调用相机");
                    goToCamera();
                    break;
                case R.id.popup_button_album://调用相册
                    myPopupWindow.dismiss();
                    LogUtils.showToastA(UserInfoActivity.this, "调用相册");
                    goToAloum();
                    break;
            }
        }
    };

    public void goToCamera() {
        File file1 = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //存储位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file1));
        startActivityForResult(intent, 300);
    }

    private void goToAloum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101) {  //相册
                try {
                    Uri uri = data.getData();
                    crop(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 200) {    //裁剪
                Bitmap bitmap = data.getParcelableExtra("data");

                if (bitmap != null) {
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75,
                                new FileOutputStream(file));

//                        showPhoto(getAbsolutePath(this, Uri.fromFile(file)),civ_user_icon);
                        String path = "http://www.meirixue.com/api.php?c=passport&a=savelog";
                        uploadIcon(file, path);

//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("imgurl", getAbsolutePath(this, Uri.fromFile(file)));
//                        MyBaseData myBaseData = new MyBaseData();
//                        myBaseData.getDate(true, false, "http://www.meirixue.com", "http://www.meirixue.com/api.php?c=passport&a=savelog", 0, 0, BaseDate.postData, map);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                civ_user_icon.setImageBitmap(bitmap);

            } else if (requestCode == 300) {    //相机
                Uri uri = Uri.fromFile(file);
                crop(uri);
            }
        }
    }

    public void uploadIcon(File file, String path) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("imgurl", file);
        CookieStore cookie = null;
        httpUtils.configCookieStore(cookie);
        httpUtils.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                LogUtils.i("userinfo", result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.i("userinfo", s);
            }
        });
    }

    public String getAbsolutePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void showPhoto(String path, ImageView photo) {

        if (path.equals(""))
            return;
        // 缩放图片, width, height 按相同比例缩放图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int scale = (int) (options.outWidth / (float) 300);
        if (scale <= 0)
            scale = 1;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);

        photo.setImageBitmap(bitmap);
        photo.setMaxHeight(350);
    }

    public void crop(Uri url) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(url, "image/*");// mUri是已经选择的图片Uri
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);// 输出图片大小
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");//输出图片格式
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 200);
    }

    class MyBaseData extends BaseDate {

        @Override
        protected void setResultError(ShowingPage.StateType stateLoadError) {

        }

        @Override
        public void setResultData(String data) {
            Gson gson = new Gson();
            LogUtils.i("userinfo", "data=" + data);
//            CookieBack cookieBack = gson.fromJson(data, CookieBack.class);
//            int status = cookieBack.getStatus();
//            if (status == 200){//未登录 跳转到登录界面
//                SharedPreferencesUtils.saveString(this,"img",);
//                return;
//            }
//            Toast.makeText(getActivity(), "数量是"+cookieBack.getCount(), Toast.LENGTH_SHORT).show();
//            JumpUtils.jumpOnly(getActivity(),UserCourseActivity.class);
            //登录之后 跳转到我的课程界面
//            List<CookieBack.DataBean> dataBeen = cookieBack.getData();
//            LogUtils.showToastA(getActivity(), status+backInfo.getStatus());
        }
    }
}
