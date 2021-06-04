package com.example.qrscaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity2 : AppCompatActivity() {

    private lateinit var textview_random: TextView
//    private lateinit var scannerService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textview_random = findViewById(R.id.xer)
        val text = intent.getStringExtra("text")

        val retrofit: RetrofitClient = RetrofitClient
        val scannerService: RetrofitServices = retrofit.getClient(
            "https://scanner-oleg.herokuapp.com").create(RetrofitServices::class.java)
        if (text != null) {
            scannerService.getCheck().enqueue(object : Callback<List<Item>>{
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val check: List<Item> = response.body()!!
                   // textview_random.text = response.body().toString()
//                    var result: String = ""
//                    for (item in check) {
//                        result += item.toString() + "\n"
//                    }
                    textview_random.text = check.toString()
                    if(true){

                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
//                    Toast.makeText("test").show()
                    if (true){

                    }
                }
            })
        }
    }

    object RetrofitClient {
        private var retrofit: Retrofit? = null

        fun getClient(baseUrl: String): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }

    interface RetrofitServices {
        @GET("/check")
        fun getCheck(): Call<List<Item>>


    }

}