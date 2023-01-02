package com.example.gar.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PageViewModel(application: Application): AndroidViewModel(application) {

    private val repository : PageRepository

    private var allData: LiveData<List<Page>>

    init {
        val pageDao = PageDatabase.getDatabase(application).pageDao()
        repository = PageRepository(pageDao)
        allData = repository.allData
    }

    fun addPage(page: Page){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPage(page)
        }
    }
}