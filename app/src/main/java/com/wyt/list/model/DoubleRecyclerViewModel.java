package com.wyt.list.model;

import java.util.ArrayList;

/**
 * Created by Won on 2016/12/22.
 */

public class DoubleRecyclerViewModel {

    private String info;

    private ArrayList<String> infos;

    public DoubleRecyclerViewModel(String info, ArrayList<String> infos) {
        this.info = info;
        this.infos = infos;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<String> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<String> infos) {
        this.infos = infos;
    }
}
