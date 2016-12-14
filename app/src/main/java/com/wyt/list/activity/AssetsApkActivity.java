package com.wyt.list.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wyt.list.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetsApkActivity extends AppCompatActivity {

    private static final String TAG = "AssetsApkActivity";

    @BindView(R.id.install)
    Button install;

    private String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zspt.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_apk);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

    }

    @OnClick(R.id.install)
    public void onClick() {
        if (copyApkFromAssets(this, "zspt.apk", FILEPATH)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + FILEPATH), "application/vnd.android.package-archive");
            startActivity(intent);
        }
    }

    public boolean copyApkFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

}
