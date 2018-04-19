package com.example.ewaew.ball.Sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.ewaew.ball.Constants;

/**
 * Created by Ewa Lyko on 19.04.2018.
 */

public class LightData implements SensorEventListener {
    private SensorManager manager;
    private Sensor light;

    private float lightData;

    public LightData() {
        manager =(SensorManager) Constants.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    public void register()
    {
        manager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public float getLightData()
    {
        return lightData;
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightData = sensorEvent.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
