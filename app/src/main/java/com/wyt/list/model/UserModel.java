package com.wyt.list.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Won on 2016/11/11.
 */

public class UserModel extends RealmObject {

    @PrimaryKey
    private String name;
    private int age;

    public UserModel() {
    }

    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
