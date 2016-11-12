package com.wyt.list.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wyt.list.R;
import com.wyt.list.adapter.MainAdapter;
import com.wyt.list.assist.IOnItemClickListener;
import com.wyt.list.assist.SpaceItemDecoration;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnItemClickListener {

    private RecyclerView list;

    private MainAdapter mainAdapter;

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> tips = new ArrayList<>();

    //用于判断跳转Intent类型
    private static final int REQUECT_CODE_SDCARD = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_space);
        list.addItemDecoration(new SpaceItemDecoration(spacingInPixels, true));//间距
        initData();
        mainAdapter = new MainAdapter(this, names, tips);
        list.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);

        //Android6.0以上要申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

    }

    private void initData() {
        names.add("Socket Demo");
        tips.add("仅用于个人测试");
        names.add("Unfile Demo");
        tips.add("解压文件与删除整个文件夹里的文件");
        names.add("Timer Demo");
        tips.add("倒计时Demo");
        names.add("MultiMedia Demo");
        tips.add("多媒体Demo(拍照与从相册选照片)");
        names.add("DrawerLayout Demo");
        tips.add("官方控件DrawerLayout的简单使用,支持左侧、右侧抽屉");
        names.add("CustomProgressBar Demo");
        tips.add("自定义进度条");
        names.add("MPAndroidLineChart Demo");
        tips.add("折线图 Demo");
        names.add("EventBus Demo");
        tips.add("EventBus简单使用 Demo");
        names.add("Realm Demo");
        tips.add("Realm数据库的简单使用");
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent;
        switch (position) {
            case 0://Socket Demo
                intent = new Intent(this, SocketActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 1://Unfile Demo
                intent = new Intent(this, UnFileActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 2://Timer Demo
                intent = new Intent(this, TimerActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 3://MultiMedia Demo
                intent = new Intent(this, MultiMediaActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 4://DrawerLayout Demo
                intent = new Intent(this, DrawerLayoutActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 5://CustomProgressBar Demo
                intent = new Intent(this, CustomProgressBarActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 6://MPAndroidLineChart Demo
                intent = new Intent(this, MPAndroidLineChartActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 7://EventBus Demo
                intent = new Intent(this, EventBusActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            case 8://Realm Demo
                intent = new Intent(this, RealmActivity.class);
                intent.putExtra("title", names.get(position));
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 授权回调接口
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 授权成功
     */
    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
        Toast.makeText(this, "成功获取内存权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * 授权失败
     */
    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        Toast.makeText(this, "没有读取内存权限", Toast.LENGTH_SHORT).show();
    }
}
