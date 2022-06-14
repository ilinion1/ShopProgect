package com.stt.shopprogect.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stt.shopprogect.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    private val viewModel by lazy { ViewModelProvider(this)[ShopItemViewModel::class.java] }
//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = ShopItem.UNDEFINED_ID
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityShopItemBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        parseIntent()
//        launchRightMode()
//    }
//
//    private fun launchRightMode()  {
//    when (screenMode) {
//        MODE_EDIT -> launchEditMode()
//        MODE_ADD -> launchAddMode()
//    }
//}
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this){
//            binding.etName.setText(it.name)
//            binding.etCount.setText(it.count.toString())
//        }
//        binding.btSave.setOnClickListener {
//            if (!validTextName()) {
//                binding.tilName.error = "Empty name"
//                checkTextNameChange()
//            }
//            if (!validTextCount()) {
//                binding.tilCount.error = "Empty count"
//                checkTextCountChange()
//            }
//            if (validTextName() && validTextCount()) {
//                viewModel.editShopItem(binding.etName.text.toString(), binding.etCount.text.toString())
//                finish()
//            }
//        }
//    }
//
//    private fun launchAddMode() {
//        binding.btSave.setOnClickListener {
//            if (!validTextName()) {
//                binding.tilName.error = "Empty name"
//                checkTextNameChange()
//            }
//            if (!validTextCount()) {
//                binding.tilCount.error = "Empty count"
//                checkTextCountChange()
//            }
//            if (validTextName() && validTextCount()) {
//                val name = binding.etName.text.toString()
//                val count = binding.etCount.text.toString()
//                viewModel.addShopItem(name, count)
//                Log.d("test", "$name")
//                finish()
//            }
//        }
//    }
//
//    private fun validTextName(): Boolean {
//        return binding.etName.text?.isBlank() == false
//    }
//
//    private fun validTextCount(): Boolean {
//        return binding.etCount.text?.isBlank() == false
//    }
//
//    private fun checkTextNameChange() {
//        binding.etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                binding.tilName.error = null
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//        })
//    }
//
//    private fun checkTextCountChange() {
//        binding.etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                binding.tilCount.error = null
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//        })
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent?.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_ITEM_ID)) {
//                throw RuntimeException("Param item id is absent")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//    }
//
//
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ITEM_ID = "extra_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ITEM_ID, shopItemId)
            return intent
        }
    }
}
