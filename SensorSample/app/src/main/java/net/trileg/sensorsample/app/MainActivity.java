package net.trileg.sensorsample.app;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {

  private SensorManager mSensorManager;
  private Sensor mAccelerometer;
  private Sensor mLinearAcceleration;
  private Sensor mGyroscope;

  private TextView accelerometerTv;
  private TextView linearAccelerationTv;
  private TextView gyroscopeTv;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    mLinearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    accelerometerTv = (TextView)findViewById(R.id.accelerometerValue);
    linearAccelerationTv = (TextView)findViewById(R.id.linearValue);
    gyroscopeTv = (TextView)findViewById(R.id.gyroscopeValue);
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
      accelerometerTv.setText(String.valueOf(event.values[0]) + "\n"
          + String.valueOf(event.values[1]) + "\n"
          + String.valueOf(event.values[2]));
    }else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
      linearAccelerationTv.setText(String.valueOf(event.values[0]) + "\n"
          + String.valueOf(event.values[1]) + "\n"
          + String.valueOf(event.values[2]));
    }else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
      gyroscopeTv.setText(String.valueOf(event.values[0]) + "\n"
          + String.valueOf(event.values[1]) + "\n"
          + String.valueOf(event.values[2]));
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  @Override
  protected void onResume() {
    super.onResume();
    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    mSensorManager.registerListener(this, mLinearAcceleration, SensorManager.SENSOR_DELAY_GAME);
    mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_GAME);
  }

  @Override
  protected void onPause() {
    mSensorManager.unregisterListener(this);
    super.onPause();
  }
}
