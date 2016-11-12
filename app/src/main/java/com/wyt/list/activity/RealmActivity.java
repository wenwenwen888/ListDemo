package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wyt.list.R;
import com.wyt.list.model.UserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Won on 2016/11/12.
 */

public class RealmActivity extends AppCompatActivity {
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.editText)
    EditText editText;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        setTitle(getIntent().getStringExtra("title"));
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
    }

    @OnClick({R.id.add, R.id.delete, R.id.select, R.id.update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                add(editText.getText().toString());
                break;
            case R.id.delete:
                delete(editText.getText().toString());
                break;
            case R.id.select:
                select();
                break;
            case R.id.update:
                update(editText.getText().toString());
                break;
        }
    }

    private void update(String name) {
        if (!TextUtils.isEmpty(name)) {
            if (isDataexists(name)) {
                UserModel user = realm.where(UserModel.class).equalTo("name", name).findFirst();
                realm.beginTransaction();
                user.setAge(17);
                realm.commitTransaction();
                Toast.makeText(this, "修改数据成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "字段不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void select() {
        RealmResults<UserModel> realmusers = realm.where(UserModel.class).findAll();
        List<UserModel> users = realm.copyFromRealm(realmusers);
        for (UserModel user : users) {
            info.append("name:" + user.getName() + ",age:" + user.getAge() + "\n");
        }
    }

    private void delete(final String name) {
        if (!TextUtils.isEmpty(name)) {
            if (isDataexists(name)) {
                final UserModel user = realm.where(UserModel.class).equalTo("name", name).findFirst();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        user.deleteFromRealm();
                    }
                });
                Toast.makeText(this, "删除数据成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "字段不能为空", Toast.LENGTH_SHORT).show();
        }


    }

    private void add(String name) {
        if (!TextUtils.isEmpty(name)) {
            if (!isDataexists(name)) {
                realm.beginTransaction();
                UserModel user = realm.createObject(UserModel.class, name); // Create a new object
                user.setAge(18);
                realm.commitTransaction();
                Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "字段不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断数据是否存在
     */
    private boolean isDataexists(String name) {
        UserModel user = realm.where(UserModel.class).equalTo("name", name).findFirst();
        return user != null;
    }
}
