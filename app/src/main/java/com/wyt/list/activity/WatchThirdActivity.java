package com.wyt.list.activity;

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

public class WatchThirdActivity extends AppCompatActivity implements ObserverListener {

    @BindView(R.id.watch_click_third)
    Button watchClickThird;
    @BindView(R.id.watch_info_third)
    TextView watchInfoThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_third);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        ObserverManager.getObserverManager().add(this);
    }

    @OnClick(R.id.watch_click_third)
    public void onClick() {
        ObserverManager.getObserverManager().notifyObserver("BinGo");
    }

    @Override
    public void observerUpdate(String content) {
        watchInfoThird.setText(content);
    }

    @Override
    protected void onDestroy() {
        ObserverManager.getObserverManager().remove(this);
        super.onDestroy();
    }
}
