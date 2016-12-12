package com.wyt.list.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wyt.list.R;
import com.wyt.list.assist.CustomProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgressDialogActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private static final String TAG = "ProgressDialogActivity";

    @BindView(R.id.show_dialog1)
    Button showDialog1;
    @BindView(R.id.show_dialog2)
    Button showDialog2;

    private ProgressDialog progressDialog;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("加载中...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);

        customProgressDialog = new CustomProgressDialog(this, R.style.CustomDialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);

        progressDialog.setOnDismissListener(this);
        customProgressDialog.setOnDismissListener(this);
    }

    @OnClick({R.id.show_dialog1, R.id.show_dialog2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_dialog1:
                progressDialog.show();
                break;
            case R.id.show_dialog2:
                customProgressDialog.show();
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.e(TAG, "onDismiss");
    }
}
