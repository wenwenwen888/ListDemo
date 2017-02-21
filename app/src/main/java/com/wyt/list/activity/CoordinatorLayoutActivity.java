package com.wyt.list.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wyt.list.R;
import com.wyt.list.adapter.CoordinatorLayoutAdapter;
import com.wyt.list.assist.GridDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coordinator_appbar)
    AppBarLayout coordinatorAppbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private ArrayList<String> datas = new ArrayList<>();
    private CoordinatorLayoutAdapter coordinatorLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);

        toolbar.setTitle(getIntent().getStringExtra("title"));//标题
        setSupportActionBar(toolbar);

        initData();
        init();

    }

    private void init() {
        //recyclerView设置属性
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//list类型
        recyclerView.addItemDecoration(new GridDividerItemDecoration(this));//下划线

        coordinatorLayoutAdapter = new CoordinatorLayoutAdapter(this, datas);
        recyclerView.setAdapter(coordinatorLayoutAdapter);
    }

    private void initData() {
        for (int i = 1; i <= 20; i++) {
            datas.add("" + i);
        }
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Snackbar.make(fab, "Click", Snackbar.LENGTH_SHORT).show();
    }
}
