package com.wyt.list.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarek360.instacapture.InstaCapture;
import com.tarek360.instacapture.listener.ScreenCaptureListener;
import com.wyt.list.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenShotActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private TextView info;
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);
        setTitle(getIntent().getStringExtra("title"));

        info = (TextView) findViewById(R.id.info);
        button = (Button) findViewById(R.id.bt_screenshot);
        imageView = (ImageView) findViewById(R.id.iv_screenshot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstaCapture.getInstance(ScreenShotActivity.this).capture().setScreenCapturingListener(new ScreenCaptureListener() {

                    @Override
                    public void onCaptureStarted() {
                        //TODO..
                        Log.e(TAG, "onCaptureStarted");
                    }

                    @Override
                    public void onCaptureFailed(Throwable e) {
                        //TODO..
                        Log.e(TAG, "onCaptureFailed" + e);
                        info.setText("截屏失败");
                    }

                    @Override
                    public void onCaptureComplete(Bitmap bitmap) {
                        //TODO..
                        Log.e(TAG, "onCaptureComplete");
                        info.setText("截屏成功");
                        imageView.setImageBitmap(bitmap);
                        Log.e(TAG, "bitmap1" + bitmap.getRowBytes() * bitmap.getHeight());
                        Log.e(TAG, "bitmap2" + bitmap.getByteCount());
                        saveMyBitmap(bitmap, "截屏测试");
                    }
                });
            }
        });

    }

    public void saveMyBitmap(Bitmap mBitmap, String bitName) {
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + bitName + ".jpg";
        File f = new File(path);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
