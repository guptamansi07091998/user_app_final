package com.example.navigation

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        var helper1 = helper(requireActivity().application)
        var db = helper1.readableDatabase
        var btn = view.findViewById<Button>(R.id.button3_si)

        var number1 = view.findViewById<EditText>(R.id.phone_si)


        btn.setOnClickListener {
            //Log.d("hii", number1.toString())
            var numb = number1.text.toString()
            val selectQuery = "SELECT  * FROM user_d WHERE number = $numb"
            Log.d("hi", numb)
            val cursor = db.rawQuery(selectQuery, null)
            //var bundle:Bundle=Bundle()




            if (cursor.getCount() > 0) {
                if(cursor.moveToNext())
                {
                    val sharedPreferences = context?.getSharedPreferences("MySharedPref",MODE_PRIVATE)
                    val editor:SharedPreferences.Editor = sharedPreferences!!.edit()
                    editor.apply{
                        editor.putString("number_extract",number1.text.toString())
                    }.apply()
                    val fragment1: Fragment = home()
                    val fragmentManager1 = requireActivity().supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager1.beginTransaction()
                    (context as AppCompatActivity).supportActionBar!!.title = "Home"
                    fragmentTransaction.replace(R.id.fragment_container, fragment1)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    //communicate.passData(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
                }
            }
            else{
                signup()
            }
        }

        return view
    }
    private fun signup()
    {
        val fragment: Fragment = message()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        (context as AppCompatActivity).supportActionBar!!.title = "Sign Up"
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}