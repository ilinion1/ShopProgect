package com.stt.shopprogect.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stt.shopprogect.R
import com.stt.shopprogect.databinding.ActivityShopItemBinding
import com.stt.shopprogect.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopItemBinding
    private val viewModel by lazy { ViewModelProvider(this)[ShopItemViewModel::class.java] }
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        launchRightMode()
    }

    private fun launchRightMode()  {
    when (screenMode) {
        MODE_EDIT -> launchEditMode()
        MODE_ADD -> launchAddMode()
    }
}

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this){
            binding.etName.setText(it.name)
            binding.etCount.setText(it.count.toString())
        }
        binding.btSave.setOnClickListener {
            if (!validTextName()) {
                binding.tilName.error = "Empty name"
                checkTextNameChange()
            }
            if (!validTextCount()) {
                binding.tilCount.error = "Empty count"
                checkTextCountChange()
            }
            if (validTextName() && validTextCount()) {
                viewModel.editShopItem(binding.etName.text.toString(), binding.etCount.text.toString())
                finish()
            }
        }
    }

    private fun launchAddMode() {
        binding.btSave.setOnClickListener {
            if (!validTextName()) {
                binding.tilName.error = "Empty name"
                checkTextNameChange()
            }
            if (!validTextCount()) {
                binding.tilCount.error = "Empty count"
                checkTextCountChange()
            }
            if (validTextName() && validTextCount()) {
                val name = binding.etName.text.toString()
                val count = binding.etCount.text.toString()
                viewModel.addShopItem(name, count)
                Log.d("test", "$name")
                finish()
            }
        }
    }

    private fun validTextName(): Boolean {
        return binding.etName.text?.isBlank() == false
    }

    private fun validTextCount(): Boolean {
        return binding.etCount.text?.isBlank() == false
    }

    private fun checkTextNameChange() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilName.error = null
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun checkTextCountChange() {
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilCount.error = null
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent?.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_ITEM_ID)) {
                throw RuntimeException("Param item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }


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

//lateinit var binding: ActivityShopItemBinding
//private val viewModel by lazy { ViewModelProvider(this)[ShopItemViewModel::class.java] }
//private var screenMode = MODE_UNKNOWN
//private var shopItemId = ShopItem.UNDEFINED_ID
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityShopItemBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//    parseIntent()
//    addTextChangeListeners()
//    launchRightMode()
//    observeViewModel()
//}
//
//private fun observeViewModel() {
//    viewModel.errorInputCount.observe(this) {
//        val message = if (it) {
//            getString(R.string.error_input_count)
//        } else {
//            null
//        }
//        binding.tilCount.error = message
//    }
//    viewModel.errorInputName.observe(this) {
//        val message = if (it) {
//            getString(R.string.error_input_name)
//        } else {
//            null
//        }
//        binding.tilName.error = message
//    }
//    viewModel.shouldCloseScreen.observe(this) {
//        finish()
//    }
//}
//
//private fun launchRightMode() {
//    when (screenMode) {
//        MODE_EDIT -> launchEditMode()
//        MODE_ADD -> launchAddMode()
//    }
//}
//
//private fun addTextChangeListeners() {
//    binding.etName.addTextChangedListener(object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            viewModel.resetErrorInputName()
//        }
//
//        override fun afterTextChanged(s: Editable?) {
//        }
//    })
//    binding.etCount.addTextChangedListener(object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            viewModel.resetErrorInputCount()
//        }
//
//        override fun afterTextChanged(s: Editable?) {
//        }
//    })
//}
//
//private fun launchEditMode() {
//    viewModel.getShopItem(shopItemId)
//    viewModel.shopItem.observe(this) {
//        binding.etName.setText(it.name)
//        binding.etCount.setText(it.count.toString())
//    }
//    binding.btSave.setOnClickListener {
//        viewModel.editShopItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
//    }
//}
//
//private fun launchAddMode() {
//    binding.btSave.setOnClickListener {
//        viewModel.addShopItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
//    }
//}
//
//private fun parseIntent() {
//    if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//        throw RuntimeException("Param screen mode is absent")
//    }
//    val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//    if (mode != MODE_EDIT && mode != MODE_ADD) {
//        throw RuntimeException("Unknown screen mode $mode")
//    }
//    screenMode = mode
//    if (screenMode == MODE_EDIT) {
//        if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//            throw RuntimeException("Param shop item id is absent")
//        }
//        shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//    }
//}
//
//companion object {
//
//    private const val EXTRA_SCREEN_MODE = "extra_mode"
//    private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
//    private const val MODE_EDIT = "mode_edit"
//    private const val MODE_ADD = "mode_add"
//    private const val MODE_UNKNOWN = ""
//
//    fun newIntentAddItem(context: Context): Intent {
//        val intent = Intent(context, ShopItemActivity::class.java)
//        intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
//        return intent
//    }
//
//    fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
//        val intent = Intent(context, ShopItemActivity::class.java)
//        intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
//        intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
//        return intent
//    }
//}
//}