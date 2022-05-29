package com.stt.shopprogect.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.stt.shopprogect.databinding.ActivityMainBinding
import com.stt.shopprogect.domain.ShopItem

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this){
            Log.d("test2", "$it")
        }
//        mainViewModel.deleteShopItem(ShopItem("Name 3", 3, true, 3))
//        mainViewModel.changeEnabledState(ShopItem("name2", 2, true, 2))
    }
}