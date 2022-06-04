package com.stt.shopprogect.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stt.shopprogect.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemName = view.findViewById<TextView>(R.id.tbName)
    val itemCount = view.findViewById<TextView>(R.id.tvCount)
}