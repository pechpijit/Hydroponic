package com.chayanin.hydroponic.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RelayModel {
    private int manualPum1;
    private int manualPum2;
    private int manualSo1;
    private int manualSo2;
    private int pum1;
    private int pum2;
    private int so1;
    private int so2;

    public int getManualPum1() {
        return manualPum1;
    }

    public void setManualPum1(int manualPum1) {
        this.manualPum1 = manualPum1;
    }

    public int getManualPum2() {
        return manualPum2;
    }

    public void setManualPum2(int manualPum2) {
        this.manualPum2 = manualPum2;
    }

    public int getManualSo1() {
        return manualSo1;
    }

    public void setManualSo1(int manualSo1) {
        this.manualSo1 = manualSo1;
    }

    public int getManualSo2() {
        return manualSo2;
    }

    public void setManualSo2(int manualSo2) {
        this.manualSo2 = manualSo2;
    }

    public int getPum1() {
        return pum1;
    }

    public void setPum1(int pum1) {
        this.pum1 = pum1;
    }

    public int getPum2() {
        return pum2;
    }

    public void setPum2(int pum2) {
        this.pum2 = pum2;
    }

    public int getSo1() {
        return so1;
    }

    public void setSo1(int so1) {
        this.so1 = so1;
    }

    public int getSo2() {
        return so2;
    }

    public void setSo2(int so2) {
        this.so2 = so2;
    }
}
