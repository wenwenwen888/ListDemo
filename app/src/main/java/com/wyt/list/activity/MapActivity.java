package com.wyt.list.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.wyt.list.R;
import com.wyt.list.util.amap.ClusterClickListener;
import com.wyt.list.util.amap.ClusterItem;
import com.wyt.list.util.amap.ClusterOverlay;
import com.wyt.list.util.amap.ClusterRender;
import com.wyt.list.util.amap.RegionItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Won on 2017/8/17.
 */

public class MapActivity extends AppCompatActivity implements AMap.OnMyLocationChangeListener, AMap.OnMapClickListener, AMap.OnInfoWindowClickListener, ClusterRender, ClusterClickListener {

    private static final String TAG = "MapActivity";

    // 获取位置要申请的权限
    public static String[] location_permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private MapView mMapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    //自定义地图的样式路径
    private String DATA_PATH = Environment.getExternalStorageDirectory() + "/map_style.data";
    //农工商位置
    private LatLng centerPoint = new LatLng(23.282224, 113.615355);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle(getIntent().getStringExtra("title"));

        //申请位置权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(this, location_permissions[0]);
            int j = ContextCompat.checkSelfPermission(this, location_permissions[1]);
            if (i != PackageManager.PERMISSION_GRANTED || j != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(location_permissions, 123);
            }
        }

        mMapView = (MapView) findViewById(R.id.map);
        //创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        //自定义地图
        aMap.setMapCustomEnable(true);
        aMap.setCustomMapStylePath(DATA_PATH);
        //改变地图的中心点
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(centerPoint, 16, 0, 0));
        aMap.animateCamera(mCameraUpdate);
        myLocationStyle = new MyLocationStyle();
        //定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置连续定位模式下的定位间隔 单位为毫秒
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点 , 默认false
        aMap.setMyLocationEnabled(true);
        //获取经纬度信息
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setOnInfoWindowClickListener(this);

        clusterOverlay = new ClusterOverlay(aMap, items, dp2px(this, 100), this);
        //设置渲染render和聚合点点击事件监听
        clusterOverlay.setClusterRenderer(this);
        clusterOverlay.setOnClusterClickListener(this);

    }

    private int marker_num = 1;
    private List<ClusterItem> items = new ArrayList<>();
    private ClusterOverlay clusterOverlay;
    private Map<Integer, Drawable> mBackDrawAbles = new HashMap<>();

    @Override
    public void onMapClick(LatLng latLng) {
        RegionItem regionItem = new RegionItem(latLng, "第" + marker_num + "个点");
//        items.add(regionItem);
        clusterOverlay.addClusterItem(regionItem);
//        aMap.addMarker(new MarkerOptions().position(latLng).title("第" + marker_num + "个点").snippet("DefaultMarker"));
        marker_num++;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.e(TAG, "getLatitude: " + location.getLatitude());
        Log.e(TAG, "getLongitude: " + location.getLongitude());
    }

    /**
     * 根据聚合点的元素数目返回渲染背景样式
     */
    @Override
    public Drawable getDrawAble(int clusterNum) {
        int radius = dp2px(getApplicationContext(), 80);
        if (clusterNum == 1) {
            Drawable bitmapDrawable = mBackDrawAbles.get(1);
            if (bitmapDrawable == null) {
                bitmapDrawable =
                        getApplication().getResources().getDrawable(
                                R.drawable.icon_openmap_mark);
                mBackDrawAbles.put(1, bitmapDrawable);
            }
            return bitmapDrawable;
        } else if (clusterNum < 5) {
            Drawable bitmapDrawable = mBackDrawAbles.get(2);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(159, 210, 154, 6)));
                mBackDrawAbles.put(2, bitmapDrawable);
            }
            return bitmapDrawable;
        } else if (clusterNum < 10) {
            Drawable bitmapDrawable = mBackDrawAbles.get(3);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(199, 217, 114, 0)));
                mBackDrawAbles.put(3, bitmapDrawable);
            }

            return bitmapDrawable;
        } else {
            Drawable bitmapDrawable = mBackDrawAbles.get(4);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(235, 215, 66, 2)));
                mBackDrawAbles.put(4, bitmapDrawable);
            }
            return bitmapDrawable;
        }
    }

    /**
     * 点击聚合点的回调处理函数
     *
     * @param marker       点击的聚合点
     * @param clusterItems 聚合点所包含的元素
     */
    @Override
    public void onClick(Marker marker, List<ClusterItem> clusterItems) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (ClusterItem clusterItem : clusterItems) {
            builder.include(clusterItem.getPosition());
        }
        LatLngBounds latLngBounds = builder.build();
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    private Bitmap drawCircle(int radius, int color) {
        Bitmap bitmap = Bitmap.createBitmap(radius * 2, radius * 2,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(0, 0, radius * 2, radius * 2);
        paint.setColor(color);
        canvas.drawArc(rectF, 0, 360, true, paint);
        return bitmap;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
