package com.wyt.list.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.wyt.list.R;
import com.wyt.list.listener.ObserverListener;
import com.wyt.list.util.ObserverManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WatchActivity extends AppCompatActivity implements ObserverListener {

    @BindView(R.id.watch_click)
    AppCompatButton watchClick;
    @BindView(R.id.watch_info)
    AppCompatTextView watchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));
        ObserverManager.getObserverManager().add(this);

    }

    @OnClick(R.id.watch_click)
    public void onClick() {
        Intent intent = new Intent(this, WatchSecondActivity.class);
        intent.putExtra("title", "WatchSecondActivity");
        startActivity(intent);
    }

    @Override
    public void observerUpdate(String content) {
        watchInfo.setText(content);
    }

    @Override
    protected void onDestroy() {
        ObserverManager.getObserverManager().remove(this);
        super.onDestroy();
    }
}
