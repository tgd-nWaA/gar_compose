package com.example.gar.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PageViewModel(application: Application): AndroidViewModel(application) {

    private val repository : PageRepository

    private var allData: LiveData<List<Page>>

    init {
        val pageDao = PageDatabase.getDatabase(application).pageDao()
        repository = PageRepository(pageDao)
        allData = repository.allData
    }
}