package com.wyt.list.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.wyt.list.R;
import com.wyt.list.listener.ObserverListener;
import com.wyt.list.util.ObserverManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WatchSecondActivity extends AppCompatActivity implements ObserverListener {

    @BindView(R.id.watch_click_second)
    Button watchClickSecond;
    @BindView(R.id.watch_info_second)
    TextView watchInfoSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_second);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        ObserverManager.getObserverManager().add(this);

    }

    @OnClick(R.id.watch_click_second)
    public void onClick() {
        Intent intent = new Intent(this, WatchThirdActivity.class);
        intent.putExtra("title", "WatchThirdActivity");
        startActivity(intent);
    }

    @Override
    public void observerUpdate(String content) {
        watchInfoSecond.setText(content);
    }

    @Override
    protected void onDestroy() {
        ObserverManager.getObserverManager().remove(this);
        super.onDestroy();
    }
}
