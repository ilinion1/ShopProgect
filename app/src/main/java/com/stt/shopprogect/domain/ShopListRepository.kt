package com.stt.shopprogect.domain

interface ShopListRepository {

    fun getShopList(): List<ShopItem>

    fun getShopItem(shopItem: ShopItem): ShopItem

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem:ShopItem)
}