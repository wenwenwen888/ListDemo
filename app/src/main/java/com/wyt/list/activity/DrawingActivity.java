package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vilyever.drawingview.DrawingView;
import com.vilyever.drawingview.brush.drawing.PenBrush;
import com.wyt.list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.ColorPicker;

/**
 * Created by Won on 2016/10/15.
 */

public class DrawingActivity extends AppCompatActivity implements DrawingView.UndoRedoStateDelegate {

    private static final String TAG = "DrawingActivity";

    @BindView(R.id.drawingView)
    DrawingView drawingView;
    @BindView(R.id.undo)
    Button undo;
    @BindView(R.id.redo)
    Button redo;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.paint)
    Button paint;

    private PenBrush penBrush;//画笔

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        setTitle(getIntent().getStringExtra("title"));

        ButterKnife.bind(this);

        penBrush = PenBrush.defaultBrush();
        penBrush.setColor(getResources().getColor(R.color.colorPrimary));
        drawingView.setBrush(penBrush);
        drawingView.setUndoRedoStateDelegate(this);//监听是否可以撤销

    }

    @OnClick({R.id.undo, R.id.redo, R.id.clear, R.id.paint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.undo://上一步
                drawingView.undo();
                break;
            case R.id.redo://下一步
                drawingView.redo();
                break;
            case R.id.clear://清除
                drawingView.clear();
                break;
            case R.id.paint://选择画笔颜色
                openColorPicker();
                break;
        }
    }

    /**
     * 打开颜色选择器
     */
    private void openColorPicker() {
        ColorPicker picker = new ColorPicker(this);
        picker.setInitColor(getResources().getColor(R.color.colorPrimary));
        picker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
            @Override
            public void onColorPicked(int pickedColor) {
                penBrush.setColor(pickedColor);
                drawingView.setBrush(penBrush);
            }
        });
        picker.show();
    }

    @Override
    public void onUndoRedoStateChange(DrawingView drawingView, boolean canUndo, boolean canRedo) {
        undo.setEnabled(canUndo);
        redo.setEnabled(canRedo);
    }
}
