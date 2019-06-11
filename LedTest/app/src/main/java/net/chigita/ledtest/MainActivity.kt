package net.chigita.ledtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.chigita.ledtest.databinding.ActivityMainBinding
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.16/~pi/ledtest.php/")
            .build()
        val service = retrofit.create(LedApi::class.java)

        binding.switch0.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 0 else 1
            service.switchLed(0, nextState)
        }

        binding.switch1.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 0 else 1
            service.switchLed(1, nextState)
        }

        binding.switch0.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 0 else 1
            service.switchLed(2, nextState)
        }

        binding.switch0.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 0 else 1
            service.switchLed(3, nextState)
        }

        binding.switch0.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 0 else 1
            service.switchLed(4, nextState)
        }



    }
}
