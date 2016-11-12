package com.wyt.list.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.util.SingleSocket;

/**
 * Created by Won on 2016/10/15.
 */

public class SocketActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SocketActivity";

    private TextView info;
    private EditText content;
    private Button send;

    private SingleSocket singleSocket;

    private MyReceiver myReceiver;

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            info.append(message + "\n");
            Log.e(TAG, message);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        setTitle(getIntent().getStringExtra("title"));

        info = (TextView) findViewById(R.id.tv_socket_info);
        content = (EditText) findViewById(R.id.et_socket_content);
        send = (Button) findViewById(R.id.bt_socket_send);

        doRegisterReceiver();//注册广播接收者

        singleSocket = SingleSocket.getUniqueSocket(this);//连接socket

        send.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_socket_send:
                singleSocket.sendMessage(content.getText().toString());
                break;
            default:
                break;
        }
    }

    /**
     * 注册广播接收者
     */
    private void doRegisterReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("com.wyt.socket.RECEIVER");
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
        super.onDestroy();
    }
}
