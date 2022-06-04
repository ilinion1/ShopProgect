package com.stt.shopprogect.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stt.shopprogect.domain.ShopItem
import com.stt.shopprogect.domain.ShopListRepository
import kotlin.RuntimeException
import kotlin.random.Random.Default.nextBoolean

class ShopListRepositoryImpl: ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementId = 0

    init {
        for (i in 0 until 20){
            shopList.add(ShopItem("Name $i", i, nextBoolean(), i))
        }
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        updateList()
        return shopListLiveData
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find { it.id == id} ?: throw RuntimeException("Undefined id")
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        deleteShopItem(oldItem)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLiveData.value = shopList.toList()
    }
}