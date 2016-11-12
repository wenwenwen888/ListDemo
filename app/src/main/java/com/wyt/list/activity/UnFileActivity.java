package com.wyt.list.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.util.Util;

import java.io.File;
import java.io.IOException;


/**
 * Created by Won on 2016/10/17.
 */

public class UnFileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button unfile, deletefile;
    private TextView info;

    private final String FILEPATH = Environment.getExternalStorageDirectory().getPath() + "/ListDemo/";
    private final String FILENAME = "newjiaofu572.zjf";
    //解压后的文件夹位置
    private String upfilefolderpath;

    //用于判断跳转Intent类型
    private static final int UPZIP_FILE = 0x00;
    private static final int DELETE_FILE = 0x01;

    private Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPZIP_FILE:
                    info.setText("解压文件在:" + upfilefolderpath);
                    break;
                case DELETE_FILE:
                    info.setText("删除成功");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfile);
        setTitle(getIntent().getStringExtra("title"));

        unfile = (Button) findViewById(R.id.bt_unfile_unfile);
        deletefile = (Button) findViewById(R.id.bt_unfile_delete);
        info = (TextView) findViewById(R.id.tv_unfile_info);

        unfile.setOnClickListener(this);
        deletefile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_unfile_unfile:
                unfile();
                break;
            case R.id.bt_unfile_delete:
                delete();
                break;
            default:
                break;
        }
    }

    /**
     * 解压
     */
    private void unfile() {
        final File file = new File(FILEPATH + FILENAME);
        info.setText("正在解压...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    upfilefolderpath = Util.upZipFile(file, FILEPATH);
                    message.what = UPZIP_FILE;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 删除
     */
    private void delete() {
        final File file = new File(upfilefolderpath);
        if (file.exists()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Util.RecursionDeleteFile(file);
                    Message message = new Message();
                    message.what = DELETE_FILE;
                    handler.sendMessage(message);
                }
            }).start();
        }
    }


}
