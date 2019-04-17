package net.chigita.gpstest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.chigita.gpstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
  companion object {
    const val MATRIX_SIZE = 9
  }

  private val binding by lazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
  }

  private var mutableGeoMagneticList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()
  private var mutableAccelerationList: MutableList<Float> = listOf(0.0f, 0.0f, 0.0f).toMutableList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    sensorManager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_UI)
    sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)
    binding.buttonGetOrientation.setOnClickListener {
      if (!binding.checkboxRealtime.isChecked) {
        updateOrientations()
      }
    }
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

  override fun onSensorChanged(event: SensorEvent?) {
    when (event?.sensor?.type) {
      Sensor.TYPE_MAGNETIC_FIELD -> {
        mutableGeoMagneticList = event.values.clone().toMutableList()
      }
      Sensor.TYPE_ACCELEROMETER -> {
        mutableAccelerationList = event.values.clone().toMutableList()
      }
    }
    if (binding.checkboxRealtime.isChecked) {
      updateOrientations()
    }
  }

  private fun updateOrientations() {
    val R = FloatArray(MATRIX_SIZE)
    val I = FloatArray(MATRIX_SIZE)
    val O = FloatArray(MATRIX_SIZE)
    val orientation = FloatArray(MATRIX_SIZE)
    SensorManager.getRotationMatrix(
        R,
        I,
        mutableAccelerationList.toFloatArray(),
        mutableGeoMagneticList.toFloatArray()
    )
    SensorManager.remapCoordinateSystem(
        R,
        SensorManager.AXIS_X,
        SensorManager.AXIS_Z,
        O
    )
    SensorManager.getOrientation(O, orientation)
    binding.azimuth = Math.floor(Math.toDegrees(orientation[0].toDouble()))
    binding.pitch = Math.floor(Math.toDegrees(orientation[1].toDouble()))
    binding.roll = Math.floor(Math.toDegrees(orientation[2].toDouble()))
  }
}
