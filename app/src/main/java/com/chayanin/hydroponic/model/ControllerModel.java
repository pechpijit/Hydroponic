package com.chayanin.hydroponic.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ControllerModel {
    private RelayModel relay;
    private SensorModel sensor;
    private SystemModel system;

    public RelayModel getRelay() {
        return relay;
    }

    public void setRelay(RelayModel relay) {
        this.relay = relay;
    }

    public SensorModel getSensor() {
        return sensor;
    }

    public void setSensor(SensorModel sensor) {
        this.sensor = sensor;
    }

    public SystemModel getSystem() {
        return system;
    }

    public void setSystem(SystemModel system) {
        this.system = system;
    }
}
