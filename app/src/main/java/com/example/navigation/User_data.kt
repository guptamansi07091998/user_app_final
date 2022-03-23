package com.example.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class User_data():ViewModel(){
 val bundleFromFragmentBToFragmentA = MutableLiveData<Bundle>()
   val edit_data=MutableLiveData<String>()
    var name_data=MutableLiveData<CharSequence>()
    val phone_data: MutableLiveData<CharSequence> = MutableLiveData()
    val mail_data: MutableLiveData<CharSequence> = MutableLiveData()
    val address_data: MutableLiveData<CharSequence> = MutableLiveData()
    // function to set the changed
    // data from the EditTexts
    fun setData(input: CharSequence) {
     //Log.d("heelo",edit_data.value.toString())
     name_data.value = input
     //Log.d("heelo",edit_data.value.toString())
    }

 // function to get the changed data from the EditTexts
 fun getData(): MutableLiveData<CharSequence> = name_data

    fun setpData(input: CharSequence) {
        phone_data.value = input
        //Log.d("hello",phone_data.value.toString())
    }

    // function to get the changed data from the EditTexts
    fun getpData(): MutableLiveData<CharSequence> = phone_data
    fun setmData(input: CharSequence) {
        mail_data.value = input
    }

    // function to get the changed data from the EditTexts
    fun getmData(): MutableLiveData<CharSequence> = mail_data
    fun setaData(input: CharSequence) {
        address_data.value = input
    }

    // function to get the changed data from the EditTexts
    fun getaData(): MutableLiveData<CharSequence> = address_data
}
