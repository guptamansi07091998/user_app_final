package com.example.navigation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.Transliterator
import android.media.Image
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class adapter (val context: Context,val cont:List<dataItem>): RecyclerView.Adapter<adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobb = cont[position]
        holder.nam.text=hobb.name
        holder.mai.text=hobb.email
        holder.contac.text=hobb.phone
    }

    override fun getItemCount(): Int {
        return cont.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {

        val nam=itemView.findViewById<TextView>(R.id.textView)
        val mai=itemView.findViewById<TextView>(R.id.textView2)
        val contac=itemView.findViewById<TextView>(R.id.textView3)
        var btn1= itemView.findViewById<FloatingActionButton>(R.id.fabButton)
        init {
            btn1.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if(view?.id==btn1.id)
            {
                val phone = contac.text.toString()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phone")
                view.context.startActivity(intent)
            }
        }


    }
}