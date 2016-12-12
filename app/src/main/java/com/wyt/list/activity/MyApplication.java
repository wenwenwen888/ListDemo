package com.wyt.list.activity;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Won on 2016/11/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Realm
         */
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("user.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        /**
         * 二维码
         */
        ZXingLibrary.initDisplayOpinion(this);
    }

}
