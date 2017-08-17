package com.wyt.list.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.wyt.list.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumActivity extends TakePhotoAppCompatActivity {

    private static final String TAG = "AlbumActivity";

    @BindView(R.id.iv_picture)
    ImageView ivPicture;

    private TakePhoto takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        //实例化TakePhoto
        takePhoto = getTakePhoto();
        takePhoto.onEnableCompress(getCompressConfig(), true);

    }

    @OnClick({R.id.take_photo, R.id.select_from_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.take_photo:
                takePhoto.onPickFromCaptureWithCrop(getImageUri(), getCropOptions());
                break;
            case R.id.select_from_album:
                takePhoto.onPickFromGalleryWithCrop(getImageUri(), getCropOptions());
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        Log.e(TAG, "getOriginalPath: " + result.getImage().getOriginalPath());
        Bitmap bitmap = BitmapFactory.decodeFile(result.getImage().getOriginalPath());
        ivPicture.setImageBitmap(bitmap);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e(TAG, "takeFail: " + msg);
    }

    @Override
    public void takeCancel() {
        Log.e(TAG, "takeCancel: ");
    }

    /**
     * 保存图片的路径
     */
    private Uri getImageUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/take_photo/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return Uri.fromFile(file);
    }

    /**
     * 裁剪的宽高配置
     */
    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);    //设置裁剪图片宽高
        builder.setOutputX(800).setOutputY(800);    //设置保存图片宽高
        builder.setWithOwnCrop(false);               //不适用自带裁剪
        return builder.create();
    }

    /**
     * 压缩的配置
     */
    private CompressConfig getCompressConfig() {
        CompressConfig.Builder builder = new CompressConfig.Builder();
        builder.setMaxSize(200 * 1024);//限制图片最大200KB
        builder.setMaxPixel(800);   //最大像素为800
        return builder.create();
    }

}
