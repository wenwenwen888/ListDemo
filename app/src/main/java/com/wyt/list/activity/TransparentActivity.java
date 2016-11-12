package com.wyt.list.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wyt.list.R;

/**
 * Created by Won on 2016/10/15.
 */

public class TransparentActivity extends Activity {

    private static final String TAG = "TransparentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);

    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

}
