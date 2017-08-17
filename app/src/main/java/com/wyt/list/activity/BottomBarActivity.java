package com.wyt.list.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wyt.list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.bottomBar_toolbar)
    Toolbar bottomBarToolbar;
    @BindView(R.id.bottomBar_info)
    TextView bottomBarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);
        bottomBarToolbar.setTitle(getIntent().getStringExtra("title"));//标题
        //以上3个属性必须在setSupportActionBar(toolbar)之前调用
        setSupportActionBar(bottomBarToolbar);

        bottomBar.setOnTabSelectListener(this);

    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                bottomBarInfo.setText("Home");
                bottomBarToolbar.setBackgroundColor(0xffff5381);
                break;
            case R.id.tab_task:
                bottomBarInfo.setText("Task");
                bottomBarToolbar.setBackgroundColor(0xff5F3F35);
                break;
            case R.id.tab_message:
                bottomBarInfo.setText("Message");
                bottomBarToolbar.setBackgroundColor(0xff802DA6);
                break;
            case R.id.tab_shop:
                bottomBarInfo.setText("Shop");
                bottomBarToolbar.setBackgroundColor(0xffff6150);
                break;
            case R.id.tab_me:
                bottomBarInfo.setText("Me");
                bottomBarToolbar.setBackgroundColor(0xffff9600);
                break;
        }
    }
}
