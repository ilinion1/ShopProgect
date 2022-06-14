package com.stt.shopprogect.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stt.shopprogect.R
import com.stt.shopprogect.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {

    var shopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var shopItemClickListener: ((ShopItem) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            ITEM_ENABLED -> R.layout.shop_item_enabled
            ITEM_DISABLED -> R.layout.shop_item_disabled
            else -> throw RuntimeException("Unknown viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.itemName.text = getItem(position).name
        holder.itemCount.text = getItem(position).count.toString()
        holder.itemView.setOnLongClickListener {
            shopItemLongClickListener?.invoke(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            shopItemClickListener?.invoke(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            ITEM_ENABLED
        } else ITEM_DISABLED
    }

    companion object {
        const val ITEM_ENABLED = 0
        const val ITEM_DISABLED = 1
        const val MAX_POOL_SIZE = 5
    }
}