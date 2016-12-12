package com.wyt.list.util;

import com.wyt.list.listener.ObserverListener;
import com.wyt.list.listener.SubjectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won on 2016/11/30.
 */

public class ObserverManager implements SubjectListener {

    public static ObserverManager observerManager;

    private List<ObserverListener> observerListeners = new ArrayList<>();

    public static ObserverManager getObserverManager() {
        if (observerManager == null) {
            synchronized (ObserverManager.class) {
                if (observerManager == null) {
                    observerManager = new ObserverManager();
                }
            }
        }
        return observerManager;
    }

    @Override
    public void add(ObserverListener observerListener) {
        observerListeners.add(observerListener);
    }

    @Override
    public void remove(ObserverListener observerListener) {
        if (observerListeners.contains(observerListener))
            observerListeners.remove(observerListener);
    }

    @Override
    public void notifyObserver(String content) {
        for (ObserverListener observerListener : observerListeners) {
            observerListener.observerUpdate(content);
        }
    }
}
