package net.chigita.ledtest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by chigichan24 on 2019-06-11.
 */

interface LedApi {
    @GET("/")
    fun switchLed(@Query("num") number: Int, @Query("stat") status: Int): Call<Unit>
}