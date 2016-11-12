package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.wyt.list.R;

/**
 * Created by Won on 2016/10/17.
 */

public class CustomProgressBarActivity extends AppCompatActivity {

    private ProgressBar pb_main_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customprogressbar);
        setTitle(getIntent().getStringExtra("title"));

        pb_main_record = (ProgressBar)findViewById(R.id.pb_main_record);

//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) pb_main_record.getLayoutParams();
//        linearParams.height = 365;

        pb_main_record.setProgress(50);
//        pb_main_record.setLayoutParams(linearParams);

    }
}
