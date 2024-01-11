package com.bronyst.musica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

//        build retrofit
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

//        get retrofit data
        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MusicData?> {
            override fun onResponse(
                call: Call<MusicData?>,
                response: Response<MusicData?>
            ) {
//           executed if method call is success
                val dataList = response.body()?.data!!
                musicAdapter = MusicAdapter(this@MainActivity, dataList)
                recyclerView.adapter = musicAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG OnResponse", "Response " + response)
            }

            override fun onFailure(call: Call<MusicData?>, t: Throwable) {
//                executed if failure
                Log.d("TAG onError", "onFailure: " + t.message)
            }
        })
    }

}