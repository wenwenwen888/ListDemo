package com.wyt.list.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.screenshotlib.InstaCapture;
import com.wyt.list.screenshotlib.listener.ScreenCaptureListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScreenShotActivity extends Activity {

    private final String TAG = "MainActivity";
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.bt_screenshot)
    Button btScreenshot;
    @BindView(R.id.iv_screenshot)
    ImageView ivScreenshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

    }

    @OnClick(R.id.bt_screenshot)
    public void onClick() {
        screenShot();
    }

    private void screenShot() {
        InstaCapture.getInstance(ScreenShotActivity.this).capture(btScreenshot).setScreenCapturingListener(new ScreenCaptureListener() {
            @Override
            public void onCaptureStarted() {
                Log.e(TAG, "onCaptureStarted");
            }

            @Override
            public void onCaptureFailed(Throwable e) {
                Log.e(TAG, "onCaptureFailed" + e);
                info.setText("截屏失败");
            }

            @Override
            public void onCaptureComplete(Bitmap bitmap) {
                Log.e(TAG, "onCaptureComplete");
                info.setText("截屏成功");
                ivScreenshot.setImageBitmap(bitmap);
                saveMyBitmap(bitmap, "截屏1");
//                saveMyBitmap(ScreenShot.takeScreenShot(ScreenShotActivity.this), "截屏2");
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
            if (fOut != null) {
                fOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fOut != null) {
                fOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
