package com.chayanin.hydroponic.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SystemModel {
    private int isSuccessRun;
    private int isSuccessStop;
    private int run;

    public int getIsSuccessRun() {
        return isSuccessRun;
    }

    public void setIsSuccessRun(int isSuccessRun) {
        this.isSuccessRun = isSuccessRun;
    }

    public int getIsSuccessStop() {
        return isSuccessStop;
    }

    public void setIsSuccessStop(int isSuccessStop) {
        this.isSuccessStop = isSuccessStop;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }
}
