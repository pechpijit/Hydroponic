package com.chayanin.hydroponic.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SensorModel {
    private double ph;
    private double templeWater;

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getTempleWater() {
        return templeWater;
    }

    public void setTempleWater(double templeWater) {
        this.templeWater = templeWater;
    }
}
