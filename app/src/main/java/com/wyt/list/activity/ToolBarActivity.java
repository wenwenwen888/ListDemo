package com.wyt.list.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wyt.list.R;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    private static final String TAG = "ToolBarActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    private SearchView searchView;
//    private SearchView.SearchAutoComplete searchEdit;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);

        toolbar.setTitle(getIntent().getStringExtra("title"));//标题
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setSubtitle("ToolBar subtitle");//副标题
//        toolbar.setSubtitleTextColor(Color.WHITE);
        //以上3个属性必须在setSupportActionBar(toolbar)之前调用
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);//导航logo

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayou);
        navigationView = (NavigationView) findViewById(R.id.navigationview);

//        navigationView.setItemIconTintList(null);//设置菜单图标恢复本来的颜色

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                Toast.makeText(ToolBarActivity.this, keyword, Toast.LENGTH_SHORT).show();
            }
        });

        //添加菜单点击事件
        toolbar.setNavigationOnClickListener(new NavigationOnClickListenerImpl());
        //添加子菜单点击事件
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClickListenerImpl());
        //右侧抽屉导航子菜单选择事件
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListenerImpl());

    }

    /**
     * 该方法是用来加载toolbar菜单布局的
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        getMenuInflater().inflate(R.menu.menu_drawer, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchEdit = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
//        searchEdit.setHintTextColor(Color.WHITE);
//        searchView.setQueryHint("输入您感兴趣的...");
//
//        //不使用默认
//        searchView.setIconifiedByDefault(false);
//
//        ImageView search_button = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);//获取搜索图标
//        search_button.setImageResource(R.drawable.ic_search_white_24dp);//图标都是用src的
//        ImageView search_close_btn = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);//获取搜索图标
//        search_close_btn.setImageResource(R.drawable.ic_close_white_24dp);//图标都是用src的
//        ImageView search_mag_icon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);//获取搜索图标
//        search_mag_icon.setImageResource(R.drawable.ic_search_white_24dp);//图标都是用src的

        return true;
    }

    /**
     * toolbar 导航logo点击事件
     */
    private class NavigationOnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    /**
     * toolbar 菜单点击事件
     */
    private class ToolbarMenuItemClickListenerImpl implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_search://因为使用android.support.v7.widget.SearchView类，可以在onCreateOptionsMenu(Menu menu)中直接设置监听事件
                    searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);
                    break;
                case R.id.action_share:
                    Snackbar.make(toolbar, "Click Share", Snackbar.LENGTH_SHORT).show();
                    break;
                case R.id.action_more:
                    Snackbar.make(toolbar, "Click More", Snackbar.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    }

    /**
     * 右侧抽屉导航子菜单选择事件
     */
    private class NavigationItemSelectedListenerImpl implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            Snackbar.make(toolbar, item.getTitle(), Snackbar.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
            return true;
        }
    }

}
