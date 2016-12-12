package com.wyt.list.activity;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wyt.list.R;

import br.com.goncalves.pugnotification.notification.PugNotification;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle(getIntent().getStringExtra("title"));
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button2)
    public void onClick() {
        showNotification(this, "测试", "test");
    }

    /**
     * 显示一个通知
     */
    public void showNotification(Context context, String title, String message) {
        PugNotification.with(context)
                .load()
                .title(title)
                .message(message)
                .smallIcon(R.drawable.ic_notifications)
                .largeIcon(R.drawable.ic_notifications)
                .flags(Notification.DEFAULT_ALL)
                .autoCancel(true)
                .simple()
                .build();
    }
}
