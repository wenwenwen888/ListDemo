package com.wyt.list.activity;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.wyt.list.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Won on 2016/11/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 奔溃日志抓取
         */
        //简单的配置，采用这种方式配置时，崩溃日志只会存在本地，默认存在 'sdcard'/Android/data/'youPackageName'/files/crash/ ,
        //当SD卡不存在或者状态异常时存贮在                          /data/data/'youPackageName'/crash/
//        AECrashHelper.initCrashHandler(this);


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

        /**
         * 字体
         */
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
