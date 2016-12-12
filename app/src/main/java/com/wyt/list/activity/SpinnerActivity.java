package com.wyt.list.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.wyt.list.R;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "SpinnerActivity";
    @BindView(R.id.nicespinner1)
    NiceSpinner nicespinner1;
    @BindView(R.id.bt_spinner_change)
    Button btSpinnerChange;
    @BindView(R.id.nicespinner2)
    NiceSpinner nicespinner2;

    private ArrayList<String> xuekes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        nicespinner1.setTextColor(Color.BLACK);
        nicespinner1.setTintColor(R.color.colorBlack);

        nicespinner2.setTextColor(Color.BLACK);
        nicespinner2.setTintColor(R.color.colorBlack);

        xuekes.add("语文");
        xuekes.add("数学");
        xuekes.add("英语");

        nicespinner1.attachDataSource(xuekes);
        nicespinner2.attachDataSource(xuekes);

        nicespinner1.addOnItemClickListener(this);
        nicespinner2.addOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.nicespinner1:
                Log.e(TAG, "onItemClick: nicespinner1:" + i);
                break;
            case R.id.nicespinner2:
                Log.e(TAG, "onItemClick: nicespinner2:" + i);
                break;
        }
    }

    @OnClick(R.id.bt_spinner_change)
    public void onClick() {
        xuekes.clear();
        xuekes.add("物理");
        xuekes.add("化学");
        xuekes.add("生物");
        nicespinner1.attachDataSource(xuekes);
        nicespinner2.attachDataSource(xuekes);
    }
}
