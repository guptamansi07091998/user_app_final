package com.example.navigation

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class home : Fragment() {
    lateinit var edit:User_data
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View=inflater.inflate(R.layout.fragment_home, container, false)
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        var str:String?=null
        str=sharedPreferences?.getString("number_extract",null)
        var name:String?=null
        var mail:String?=null
        var phone:String?=null
        var address:String?=null
        if(str.toString()!="null") {
            //Toast.makeText(context,str.toString(),Toast.LENGTH_LONG).show()
            var numb: String = str.toString()
            val selectQuery = "SELECT  * FROM user_d WHERE number = $numb"
            var helper1 = helper(requireActivity().application)
            var db = helper1.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            val i = cursor.getCount().toString()
            if (cursor.moveToNext()) {
                name = cursor.getString(1)
                mail = cursor.getString(3)
                phone = cursor.getString(2)
                address = cursor.getString(4)
            }
        }
        view.name_a.text=name.toString()
        view.mail_a.text=mail.toString()
        view.mobile_a.text=phone.toString()
        view.address_a.text=address.toString()
        var button = view.findViewById<FloatingActionButton>(R.id.fab2)
        button.setOnClickListener {
            requestPermissions()
        }
        //Toast.makeText(context,name.toString(),Toast.LENGTH_LONG).show()
        var btn=view.findViewById<FloatingActionButton>(R.id.fab)
        btn.setOnClickListener {
            val fragment: Fragment = edit()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            (context as AppCompatActivity).supportActionBar!!.title = "Edit Profile"
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return view
    }

    private fun hasWritePermission(): Boolean =
        context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_GRANTED

    private fun hasReadPermission():Boolean=
        context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions()
    {
        var arr= mutableListOf<String>()
        if(!hasWritePermission())
            arr.add( android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(!hasReadPermission())
            arr.add( android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(arr.isNotEmpty())
            ActivityCompat.requestPermissions(
                context as Activity,
                arr.toTypedArray(),
                0
            )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty() )
        {
            for(i in grantResults.indices)
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission request", "$permissions[i] granted")
                }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        edit = ViewModelProvider(requireActivity()).get(User_data::class.java)
        edit!!.getData().observe(viewLifecycleOwner, Observer {
            name_a!!.text = it

            //Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
        })
        edit!!.getpData().observe(viewLifecycleOwner, Observer {
            mobile_a!!.text = it
            var m=it
            val sharedPreferences = context?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.apply{
                editor.putString("number_extract",m.toString())
            }.apply()
        })
        edit!!.getmData().observe(viewLifecycleOwner, Observer {
            mail_a!!.text = it
        })
        edit!!.getaData().observe(viewLifecycleOwner, Observer {
            address_a!!.text = it
        })


    }

}
