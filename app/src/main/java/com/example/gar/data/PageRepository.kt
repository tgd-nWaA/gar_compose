package com.example.gar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PageRepository(private val pageDao: PageDao) {

    val allData: LiveData<List<Page>> = pageDao.readAllData()

    suspend fun addPage(page: Page){
        pageDao.addPage(page)
    }


}