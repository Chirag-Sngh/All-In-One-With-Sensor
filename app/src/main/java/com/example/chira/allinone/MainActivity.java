package com.example.chira.allinone;

import android.bluetooth.BluetoothAdapter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    WifiManager wm;
    BluetoothAdapter ba;
    MediaPlayer mp;
    CameraManager cm;
    Vibrator v1;
    SensorManager sm;
    Sensor s;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wm=(WifiManager)getSystemService(WIFI_SERVICE);
        ba=BluetoothAdapter.getDefaultAdapter();
        mp=MediaPlayer.create(this,R.raw.x);
        cm=(CameraManager)getSystemService(CAMERA_SERVICE);
        v1=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;
        if(x1!=0)
        {
            mp.start();
            wm.setWifiEnabled(true);
            ba.enable();
            try {
                String cameraid=cm.getCameraIdList()[0];
                cm.setTorchMode(cameraid,true);
            }
            catch (CameraAccessException e)
            {

            }
                if (Build.VERSION.SDK_INT>26)
                {
                    v1.vibrate(5000);
                }
                else
                {
                    v1.vibrate(5000);
                }


        }
        else
        {
            mp.pause();
            wm.setWifiEnabled(false);
            ba.disable();
            try {
                String cameraid=cm.getCameraIdList()[0];
                cm.setTorchMode(cameraid,false);

            }
            catch (CameraAccessException e)
            {

            }



        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
