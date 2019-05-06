package net.chigita.gpstest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.chigita.gpstest.databinding.ActivityMainBinding

/**
 * Created by chigichan24 on 2019-04-23.
 */

class MainActivity : AppCompatActivity() {
  private val binding by lazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(
        this,
        R.layout.activity_main
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.buttonOrientation.setOnClickListener {
      startActivity(Intent(applicationContext, OrientationActivity::class.java))
    }
    binding.buttonLocation.setOnClickListener {
      startActivity(Intent(applicationContext, LocationActivity::class.java))
    }
  }
}