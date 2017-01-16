package com.wyt.list.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wyt.list.R;
import com.wyt.list.adapter.DoubleRecyclerViewAdapter;
import com.wyt.list.assist.GridDividerItemDecoration;
import com.wyt.list.assist.IOnItemClickListener;
import com.wyt.list.model.DoubleRecyclerViewModel;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoubleRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener, IOnItemClickListener {

    private static final String TAG = "DoubleRecyclerViewActiv";

    @BindView(R.id.rv_double_recyclerView)
    RecyclerView rvDoubleRecyclerView;
    @BindView(R.id.srl_double_recyclerView)
    SwipeRefreshLayout srlDoubleRecyclerView;

    private LinearLayoutManager linearLayoutManager;

    private Button btAddItem;
    private Button btAddItemItem;

    private DoubleRecyclerViewAdapter doubleRecyclerViewAdapter;

    private HeaderAndFooterWrapper headerAndFooterWrapper;

    private ArrayList<DoubleRecyclerViewModel> doubleRecyclerViewModels = new ArrayList<>();

    private ArrayList<String> infos1 = new ArrayList<>();
    private ArrayList<String> infos2 = new ArrayList<>();
    private ArrayList<String> infos3 = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_view);
        setTitle(getIntent().getStringExtra("title"));
        ButterKnife.bind(this);

        initData();

        linearLayoutManager = new LinearLayoutManager(this);
        //recyclerView设置属性
        rvDoubleRecyclerView.setLayoutManager(linearLayoutManager);//list类型
        rvDoubleRecyclerView.addItemDecoration(new GridDividerItemDecoration(this));//下划线

        //下拉刷新组件
        srlDoubleRecyclerView.setColorSchemeResources(R.color.colorMain);
        srlDoubleRecyclerView.setEnabled(false);//禁止下拉刷新

        doubleRecyclerViewAdapter = new DoubleRecyclerViewAdapter(this, doubleRecyclerViewModels);
        /**
         * 添加headerview,footerView
         */
        headerAndFooterWrapper = new HeaderAndFooterWrapper(doubleRecyclerViewAdapter);
        View headerview = LayoutInflater.from(this).inflate(R.layout.double_recyvlerview_headerview, (ViewGroup) this.getWindow().getDecorView(), false);
        btAddItem = (Button) headerview.findViewById(R.id.bt_add_item);
        btAddItemItem = (Button) headerview.findViewById(R.id.bt_add_item_item);
        headerAndFooterWrapper.addHeaderView(headerview);
        View footerView = LayoutInflater.from(this).inflate(R.layout.footerview, (ViewGroup) this.getWindow().getDecorView(), false);
        headerAndFooterWrapper.addFootView(footerView);
        rvDoubleRecyclerView.setAdapter(headerAndFooterWrapper);
        headerAndFooterWrapper.notifyDataSetChanged();

//        doubleRecyclerViewAdapter = new DoubleRecyclerViewAdapter(this, doubleRecyclerViewModels);
//        rvDoubleRecyclerView.setAdapter(doubleRecyclerViewAdapter);

        btAddItem.setOnClickListener(this);
        btAddItemItem.setOnClickListener(this);

        doubleRecyclerViewAdapter.setOnItemClickListener(this);

        rvDoubleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {// 第一个item出现时
                    Log.e(TAG, "第一个item出现时啦~");
                }
                if (!recyclerView.canScrollVertically(1) && dy != 0) {// 手指不能向上滑动了
                    srlDoubleRecyclerView.setRefreshing(true);//刷新图标出现
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    srlDoubleRecyclerView.setRefreshing(false);
                                }
                            });
                        }
                    }).start();
                }
            }
        });

    }


    private void initData() {
        infos1.add("java");
        infos1.add("c++");

        infos2.add("WindowsPhone");
        infos2.add("Android");
        infos2.add("Iphone");

        infos3.add("lol");
        infos3.add("ow");
        infos3.add("wow");

        doubleRecyclerViewModels.add(new DoubleRecyclerViewModel("语言", infos1));
        doubleRecyclerViewModels.add(new DoubleRecyclerViewModel("移动平台", infos2));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add_item:
                doubleRecyclerViewModels.add(new DoubleRecyclerViewModel("游戏", infos3));
                headerAndFooterWrapper.notifyDataSetChanged();
                break;
            case R.id.bt_add_item_item:
                doubleRecyclerViewModels.get(0).getInfos().add("php");
                headerAndFooterWrapper.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e(TAG, "position: " + position);
    }
}
