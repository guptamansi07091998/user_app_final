package com.example.navigation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val url="https://jsonplaceholder.typicode.com/"
class contacts : Fragment(){
    lateinit var myAdapter:adapter
    lateinit var layout: LinearLayout
    lateinit var newrecycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_contacts, container, false)

        newrecycler=view.findViewById(R.id.recyclerview)
       newrecycler.setHasFixedSize(true)
        newrecycler.layoutManager= LinearLayoutManager(context)

        getMyData()

        return view
    }
    private fun getMyData() {
        val builder= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build().create(api::class.java)
        val retrofitdata=builder.getData()
        retrofitdata.enqueue(object : Callback<List<dataItem>?> {
            override fun onResponse(
                call: Call<List<dataItem>?>?,
                response: Response<List<dataItem>?>?
            ) {
                val responseBody=response!!.body()
                myAdapter= context?.let { adapter(it,responseBody!! ) }!!
                myAdapter.notifyDataSetChanged()
                newrecycler.adapter=myAdapter

            }

            override fun onFailure(call: Call<List<dataItem>?>?, t: Throwable?) {

            }
        })
    }
    private fun checkPermissions() {

    }
}