package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wyt.list.R;
import com.wyt.list.model.UserModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusSecondActivity extends AppCompatActivity {


    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbussecond);
        setTitle("EventBusSecondActivity");
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onClick() {
        UserModel userModel = new UserModel();
        userModel.setName("cyy");
        userModel.setAge(18);
        EventBus.getDefault().post(userModel);
    }
}
