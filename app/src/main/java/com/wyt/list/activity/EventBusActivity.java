package com.wyt.list.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.model.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Won on 2016/11/12.
 */

public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.info1)
    TextView info1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        setTitle(getIntent().getStringExtra("title"));
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @OnClick(R.id.info1)
    public void onClick() {
        Intent intent = new Intent(this, EventBusSecondActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserModel userModel) {
        info1.setText("name = " + userModel.getName() + ",age = " + userModel.getAge());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
