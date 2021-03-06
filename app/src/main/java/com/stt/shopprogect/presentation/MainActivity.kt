package com.stt.shopprogect.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.stt.shopprogect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ShopListAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this){
            adapter.submitList(it)
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener()
        binding.floatBar.setOnClickListener {
            startActivity(ShopItemActivity.newIntentAddItem(this))
        }
    }

    private fun setupSwipeListener(){
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                mainViewModel.deleteShopItem(item)
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.rcId)

    }

    private fun setupClickListener() {
        adapter.shopItemClickListener = {
            startActivity(ShopItemActivity.newIntentEditItem(this, it.id))
        }
    }

    private fun setupLongClickListener() {
        adapter.shopItemLongClickListener = {
            mainViewModel.changeEnabledState(it)
        }
    }

    private fun setupRecyclerView(){
        adapter = ShopListAdapter()
        binding.rcId.adapter = adapter
        binding.rcId.recycledViewPool.setMaxRecycledViews(ShopListAdapter.ITEM_ENABLED, ShopListAdapter.MAX_POOL_SIZE)
        binding.rcId.recycledViewPool.setMaxRecycledViews(ShopListAdapter.ITEM_DISABLED, ShopListAdapter.MAX_POOL_SIZE)
    }

}