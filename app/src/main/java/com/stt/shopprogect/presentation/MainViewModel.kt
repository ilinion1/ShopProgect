package com.stt.shopprogect.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stt.shopprogect.data.ShopListRepositoryImpl
import com.stt.shopprogect.domain.DeleteShopItemUseCase
import com.stt.shopprogect.domain.EditShopItemUseCase
import com.stt.shopprogect.domain.GetShopListUseCase
import com.stt.shopprogect.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }
}