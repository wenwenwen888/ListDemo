package com.wyt.list.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.wyt.list.R;
import com.wyt.list.assist.MyMarkerView;
import com.wyt.list.util.DateXAxisFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Won on 2016/11/9.
 */

public class MPAndroidLineChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    @BindView(R.id.linechart)
    LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroidlinechart);

        ButterKnife.bind(this);

        init();

    }
    private void init() {
//        mChart.setOnChartGestureListener(this);//手势
        mChart.setOnChartValueSelectedListener(this);//数值选择
//        mChart.setDrawGridBackground(true);//设置网格背景

        //设置描述
        Description description = new Description();
        description.setText("日期");
        description.setTextSize(15f);
        mChart.setDescription(description);
//        mChart.getDescription().setEnabled(false);//不设置描述

        mChart.setTouchEnabled(true); //设置能否触摸
        mChart.setDragEnabled(false);//设置能否拖动
        mChart.setScaleEnabled(false);//设置能否放大缩小
        // if disabled, scaling can be done on x- and y-axis separately
//        mChart.setPinchZoom(true);

        // 创建一个点击显示的MarkerView
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);//这个是显示信息的背景
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴在底部//默认顶部
        xAxis.setTextSize(10f);//设置X轴文字的大小
        xAxis.setTextColor(Color.BLACK);//设置X轴的文字的颜色
        xAxis.setAxisLineWidth(2f);//设置X轴的宽度
        xAxis.setDrawAxisLine(true);//设置是否划线
        xAxis.setAxisLineColor(Color.BLACK);//设置划线的颜色
        xAxis.setDrawGridLines(false);//设置是否需要垂直网格线
        xAxis.setValueFormatter(new DateXAxisFormatter());//自定义X轴下面的文字
//        xAxis.addLimitLine(llXAxis); //画一条垂直直线

        //画横直线
//        LimitLine ll1 = new LimitLine(0f, "日期");
//        ll1.setLineWidth(1f);
//        ll1.setLineColor(Color.BLACK);
//        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//        ll1.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // 先清除所有的直线
//        leftAxis.addLimitLine(ll1);
        leftAxis.setAxisMaximum(105f);//设置Y轴最大值
        leftAxis.setAxisMinimum(0f);//设置Y轴最小值
        leftAxis.enableGridDashedLine(10f, 10f, 0f);//设置虚线间隔
        leftAxis.setDrawZeroLine(true);//设置直线底线
//        leftAxis.setDrawTopYLabelEntry(true);//是否显示最后一个Y值
        leftAxis.setDrawLimitLinesBehindData(true);// 先画数据后画直线

        mChart.getAxisRight().setEnabled(false);//设置右侧Y轴是否不可见

        setData(10, 100);//设置数据

//        mChart.setVisibleXRange(0f, 11f);//设置X轴可见的范围

        mChart.animateX(2500);//动画时间
        mChart.invalidate();//刷新图表

        Legend l = mChart.getLegend();//获取提示数据类型
        l.setForm(Legend.LegendForm.LINE);//更改提示类型前面的图像

        mChart.invalidate();//刷新图表
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * range);
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // 设置提示数据类型
            set1 = new LineDataSet(values, "题目/道");
//            set1.enableDashedLine(10f, 5f, 0f);//这个是把折线设置虚线的
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(0xff00d3b7);//设置折线的颜色
            set1.setCircleColor(0xff00d3b7);//设置顶点的颜色
            set1.setLineWidth(1f);//设置折线的宽度
            set1.setCircleRadius(3f);//顶点圆的角度
//            set1.setDrawCircleHole(false);//设置画圆孔?
            set1.setValueTextSize(9f);//设置文本大小
            set1.setDrawFilled(true);//设置填充区域
            //设置提示数据类型的属性
            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                //填充区域只支持API>=18
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(0x5000d3b7);//小于18填充颜色区域
            }

            ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
            // add the datasets
            iLineDataSets.add(set1);

            //实例化一个LineData
            LineData lineData = new LineData(iLineDataSets);
            //设置lineData数据
            mChart.setData(lineData);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
