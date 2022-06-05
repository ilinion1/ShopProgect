package com.stt.shopprogect.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stt.shopprogect.R
import com.stt.shopprogect.databinding.ActivityShopItemBinding

class ShopItemActivity: AppCompatActivity() {
    lateinit var binding: ActivityShopItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}