package com.example.navigation

import android.view.View

interface OnItemClick {
    fun onClick(view: View, position: Int)
}