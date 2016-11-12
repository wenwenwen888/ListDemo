package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wyt.list.R;

/**
 * Created by Won on 2016/10/17.
 */

public class DrawerLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerlayout;

    private LinearLayout left, right;

    private TextView tv_left, tv_right;

    private Button button_left, button_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        setTitle(getIntent().getStringExtra("title"));

        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        left = (LinearLayout) findViewById(R.id.left);
        right = (LinearLayout) findViewById(R.id.right);

        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);

        button_left = (Button) findViewById(R.id.button_left);
        button_right = (Button) findViewById(R.id.button_right);

        //关闭手势滑动,默认开启
        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        button_left.setOnClickListener(this);
        button_right.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                drawerlayout.openDrawer(left);
                break;
            case R.id.tv_right:
                drawerlayout.openDrawer(right);
                break;
            case R.id.button_left:
                Toast.makeText(this, "没错,我是左侧", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_right:
                Toast.makeText(this, "没错,我是右侧", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
