package com.example.navigation

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.navigation.databinding.FragmentEditBinding
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_list.*

class edit : Fragment() {
    lateinit var edit:User_data
    lateinit var binding:FragmentEditBinding
    lateinit var name1:String
    lateinit var mail1:String
    lateinit var phone1:String
    lateinit var address1:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit, container, false)
        var view:View=binding.root
        val update: Button = view.findViewById(R.id.bt)
        var helper1= helper(requireActivity().application)
        var db=helper1.readableDatabase
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val str:String?=sharedPreferences!!.getString("number_extract",null)
        var numb:String=str.toString()
        val selectQuery = "SELECT  * FROM user_d WHERE number = $numb"
        val cursor = db.rawQuery(selectQuery, null)
        val i=cursor.getCount().toString()
        if(cursor.moveToNext()){
            name1= cursor.getString(1)
            mail1= cursor.getString(3)
            phone1= cursor.getString(2)
            address1= cursor.getString(4)
            //Toast.makeText(context,address1,Toast.LENGTH_LONG).show()
        }
        var n= view.findViewById<EditText>(R.id.Name1)
        var p=view.findViewById<EditText>(R.id.Phone)
        var m=view.findViewById<EditText>(R.id.EmailAddress)
        var a=view.findViewById<EditText>(R.id.Address)
        n.setText(name1)
        p.setText(phone1)
        m.setText(mail1)
        a.setText(address1)

       update.setOnClickListener {
         //  Toast.makeText(context,edit.name_data.toString(),Toast.LENGTH_LONG).show()
       edit?.setData(Name1!!.text.toString())
           edit?.setpData(Phone!!.text.toString())
           edit?.setmData(EmailAddress!!.text.toString())
           edit?.setaData(Address!!.text.toString())
           var ct= ContentValues()
           ct.put("name", Name1.text.toString())
           ct.put("number",Phone.text.toString())
           ct.put("email_address",EmailAddress.text.toString())
           ct.put("address",Address.text.toString())
           val db = helper1.writableDatabase
           db.update("user_d", ct, "number=${Phone.text.toString()}", arrayOf())

               val sharedPreferences = activity?.getSharedPreferences("MySharedPref", 0)
               sharedPreferences?.edit()?.remove("number_extract")?.commit()
           val fragment1: Fragment = home()
           val fragmentManager1 = requireActivity().supportFragmentManager
           val fragmentTransaction: FragmentTransaction = fragmentManager1.beginTransaction()
           (context as AppCompatActivity).supportActionBar!!.title = "Home"
           fragmentTransaction.replace(R.id.fragment_container, fragment1)
           fragmentTransaction.addToBackStack(null)
           fragmentTransaction.commit()


       }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      edit = ViewModelProvider(requireActivity()).get(User_data::class.java)
    }
}


