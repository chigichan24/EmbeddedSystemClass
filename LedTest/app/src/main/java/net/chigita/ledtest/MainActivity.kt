package net.chigita.ledtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.chigita.ledtest.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.11.152/")
            .client(client)
            .build()
        val service = retrofit.create(LedApi::class.java)

        binding.switch0.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 1 else 0
            Log.d("debug", nextState.toString())
            service.switchLed(0, nextState).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("debug", t.message)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("debug", "success")
                }

            })
        }

        binding.switch1.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 1 else 0
            service.switchLed(1, nextState).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("debug", t.message)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("debug", "success")
                }

            })
        }

        binding.switch2.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 1 else 0
            service.switchLed(2, nextState).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("debug", t.message)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("debug", "success")
                }

            })
        }

        binding.switch3.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 1 else 0
            service.switchLed(3, nextState).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("debug", t.message)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("debug", "success")
                }

            })
        }

        binding.switch4.setOnToggledListener { _, isOn ->
            val nextState = if (isOn) 1 else 0
            service.switchLed(4, nextState).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("debug", t.message)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("debug", "success")
                }

            })
        }



    }
}
