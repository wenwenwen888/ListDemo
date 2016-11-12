package com.wyt.list.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wyt.list.R;

/**
 * Created by Won on 2016/10/17.
 */

public class MultiMediaActivity extends AppCompatActivity implements View.OnClickListener {

    //打开相册,打开相机按钮
    private Button openalbum, opencamera;
    //预览图
    private ImageView previewicon;

    private Bitmap bitmap = null;

    //标识
    private final int ALBUM = 0x00;
    private final int CAMERA = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        setTitle(getIntent().getStringExtra("title"));

        openalbum = (Button) findViewById(R.id.openalbum);
        opencamera = (Button) findViewById(R.id.opencamera);
        previewicon = (ImageView) findViewById(R.id.previewicon);

        openalbum.setOnClickListener(this);
        opencamera.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openalbum://相册
                //调用相册
                openAlbum();
                break;
            case R.id.opencamera://相机
                //调用相机
                openCamera();
                break;
            default:
                break;
        }
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ALBUM);
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case ALBUM://相册获得的bitmap
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    String imagePath;
                    if (c != null) {
                        c.moveToFirst();
                        int columnIndex = c.getColumnIndex(filePathColumns[0]);
                        imagePath = c.getString(columnIndex);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                        bitmap = BitmapFactory.decodeFile(imagePath);
                        showPreviewIcon(bitmap);
                        c.close();
                    }
                    break;
                case CAMERA://相机获得的bitmap
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    bitmap = (Bitmap) data.getExtras().get("data");
                    showPreviewIcon(bitmap);
                    break;
                default:
                    break;
            }
        }
    }

    //加载图片
    private void showPreviewIcon(Bitmap bm) {
        previewicon.setImageBitmap(bm);
    }

    @Override
    protected void onDestroy() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        super.onDestroy();
    }
}
