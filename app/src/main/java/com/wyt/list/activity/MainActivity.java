package com.wyt.list.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.wyt.list.R;
import com.wyt.list.adapter.MainAdapter;
import com.wyt.list.assist.IOnItemClickListener;
import com.wyt.list.assist.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements IOnItemClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView list;

    private MainAdapter mainAdapter;

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> tips = new ArrayList<>();

    //用于判断跳转Intent类型
    private static final int REQUECT_CODE_SDCARD = 0x00;
    private static final int REQUECT_CODE_CAMERA = 0x01;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

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

        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(mainAdapter);
//        View headerview = LayoutInflater.from(this).inflate(R.layout.headerview, null);
//        headerAndFooterWrapper.addHeaderView(headerview);
        View footerView = LayoutInflater.from(this).inflate(R.layout.footerview, null);
        headerAndFooterWrapper.addFootView(footerView);

        list.setAdapter(headerAndFooterWrapper);
        headerAndFooterWrapper.notifyDataSetChanged();

//        list.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);

        //Android6.0以上要申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            MPermissions.requestPermissions(this, REQUECT_CODE_CAMERA, Manifest.permission.CAMERA);
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
        names.add("ScreenShot Demo");
        tips.add("一个简单的截屏工具");
        names.add("Drawing Demo");
        tips.add("一个简单的画板");
        names.add("Notification Demo");
        tips.add("Notification广播简单使用");
        names.add("CustomSearchEditText Demo");
        tips.add("一个自定义搜索框");
        names.add("Spinner Demo");
        tips.add("NiceSpinner demo");
        names.add("AssetsApk Demo");
        tips.add("从Assets文件夹安装APK");
        names.add("ProgressDialog Demo");
        tips.add("自定义ProgressDialog");
        names.add("QRCodeActivity Demo");
        tips.add("二维码demo");
        names.add("WatchActivity Demo");
        tips.add("观察者模式demo");
        names.add("AnimationActivity Demo");
        tips.add("简单的动画效果");
        names.add("ToolBar Demo");
        tips.add("ToolBar初认识");
        names.add("DoubleRecyclerView Demo");
        tips.add("RecyclerView 嵌入 RecyclerView");
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0://Socket Demo
                startActivity(SocketActivity.class, names.get(position));
                break;
            case 1://Unfile Demo
                startActivity(UnFileActivity.class, names.get(position));
                break;
            case 2://Timer Demo
                startActivity(TimerActivity.class, names.get(position));
                break;
            case 3://MultiMedia Demo
                startActivity(MultiMediaActivity.class, names.get(position));
                break;
            case 4://DrawerLayout Demo
                startActivity(DrawerLayoutActivity.class, names.get(position));
                break;
            case 5://CustomProgressBar Demo
                startActivity(CustomProgressBarActivity.class, names.get(position));
                break;
            case 6://MPAndroidLineChart Demo
                startActivity(MPAndroidLineChartActivity.class, names.get(position));
                break;
            case 7://EventBus Demo
                startActivity(EventBusActivity.class, names.get(position));
                break;
            case 8://Realm Demo
                startActivity(RealmActivity.class, names.get(position));
            case 9://ScreenShot Demo
                startActivity(ScreenShotActivity.class, names.get(position));
                break;
            case 10://Drawing Demo
                startActivity(DrawingActivity.class, names.get(position));
                break;
            case 11://Notification Demo
                startActivity(NotificationActivity.class, names.get(position));
                break;
            case 12://CustomSearchEditText Demo
                startActivity(CustomSearchEditTextActivity.class, names.get(position));
                break;
            case 13://Spinner Demo
                startActivity(SpinnerActivity.class, names.get(position));
                break;
            case 14://AssetsApk Demo
                startActivity(AssetsApkActivity.class, names.get(position));
                break;
            case 15://ProgressDialog Demo
                startActivity(ProgressDialogActivity.class, names.get(position));
                break;
            case 16://QRCode Demo
                startActivity(QRCodeActivity.class, names.get(position));
                break;
            case 17://WatchActivity Demo
                startActivity(WatchActivity.class, names.get(position));
                break;
            case 18://AnimationActivity Demo
                startActivity(AnimationActivity.class, names.get(position));
                break;
            case 19://ToolBarActivity Demo
                startActivity(ToolBarActivity.class, names.get(position));
                break;
            case 20://DoubleRecyclerView Demo
                startActivity(DoubleRecyclerViewActivity.class, names.get(position));
                break;
            default:
                break;
        }
    }

    private void startActivity(Class<?> cls, String title) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("title", title);
        startActivity(intent);
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

    /**
     * 授权成功
     */
    @PermissionGrant(REQUECT_CODE_CAMERA)
    public void requestCameraSuccess() {
        Toast.makeText(this, "成功获取相机权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * 授权失败
     */
    @PermissionDenied(REQUECT_CODE_CAMERA)
    public void requestCameraFailed() {
        Toast.makeText(this, "没有相机权限", Toast.LENGTH_SHORT).show();
    }
}
