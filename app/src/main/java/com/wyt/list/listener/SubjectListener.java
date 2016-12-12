package com.wyt.list.listener;

/**
 * 被观察者接口
 * Created by Won on 2016/11/30.
 */

public interface SubjectListener {

    void add(ObserverListener observerListener);

    void remove(ObserverListener observerListener);

    void notifyObserver(String content);

}
