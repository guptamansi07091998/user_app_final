package com.example.navigation

/*SignUP File*/

import android.app.Application
import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_home.*

import kotlinx.android.synthetic.main.fragment_message.*

class message : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_message, container, false)

        var helper1= helper(requireActivity().application)
        var db=helper1.readableDatabase
        var btn=view.findViewById<Button>(R.id.button1)
        var name=view.findViewById<EditText>(R.id.name)
        var number=view.findViewById<EditText>(R.id.number)
        var mail=view.findViewById<EditText>(R.id.email)
        var address=view.findViewById<EditText>(R.id.adress)
        btn.setOnClickListener {
            var ct= ContentValues()
            ct.put("name", name.text.toString())
            ct.put("number",number.text.toString())
            ct.put("email_address",mail.text.toString())
            ct.put("address",address.text.toString())
            db.insert("user_d",null,ct)
            val sharedPreferences = context?.getSharedPreferences("MySharedPref",MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.apply{
                editor.putString("number_extract",number.text.toString())
            }.apply()
            val fragment1: Fragment = home()
            val fragmentManager1 = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager1.beginTransaction()
            (context as AppCompatActivity).supportActionBar!!.title = "Home"
            fragmentTransaction.replace(R.id.fragment_container, fragment1)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            name.setText("")
            number.setText("")
            mail.setText("")
            address.setText("")
            name.requestFocus()
        }
        return view
    }


}