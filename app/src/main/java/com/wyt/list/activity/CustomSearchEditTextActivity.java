package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wyt.list.R;
import com.wyt.searchedittext.SearchEditText;

public class CustomSearchEditTextActivity extends AppCompatActivity implements SearchEditText.OnSearchClickListener {

    private static final String TAG = "CustomSearchEditTextAct";
    private SearchEditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search_edit_text);
        setTitle(getIntent().getStringExtra("title"));

        searchEditText = (SearchEditText) findViewById(R.id.searchEditText);

        searchEditText.setOnSearchClickListener(this);
    }

    @Override
    public void onSearchClick(View view) {
        String keyword = searchEditText.getText().toString();
        if (!TextUtils.isEmpty(keyword)) {
            //在这里处理逻辑
            Toast.makeText(this, keyword, Toast.LENGTH_SHORT).show();
        }
    }
}
