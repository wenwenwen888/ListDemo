package com.wyt.list.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wyt.list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class QRCodeActivity extends AppCompatActivity {

    private static final String TAG = "QRCodeActivity";

    @BindView(R.id.scanner_qrcode)
    Button scannerQrcode;
    @BindView(R.id.create_qrcode)
    Button createQrcode;
    @BindView(R.id.qrcodeimg)
    ImageView qrcodeimg;
    @BindView(R.id.et_qrcode)
    EditText etQrcode;

    private final static int REQUEST_CODE = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

    }

    @OnClick({R.id.scanner_qrcode, R.id.create_qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scanner_qrcode:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.create_qrcode:
                String textContent = etQrcode.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                etQrcode.setText("");
                createQRCode(textContent);
                break;
        }
    }

    private void createQRCode(String content) {
        Bitmap mBitmap = CodeUtils.createImage(content, 400, 400, null);
        qrcodeimg.setImageBitmap(mBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
