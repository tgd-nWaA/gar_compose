package com.example.gar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPage(page: Page)
//
//    @Delete
//    suspend fun deletePage(page: Page)
//
//    @Query("DELETE FROM pages WHERE id = :id")
//    suspend fun deletePageById(id: String)
//
//    @Query("DELETE FROM pages")
//    suspend fun deleteAllPages()
//
//    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'pages'")
//    suspend fun updateSqlSequence()
//
//    @Update
//    suspend fun updatePage(page: Page)
//
    @Query("SELECT * FROM pages ORDER BY Id ASC")
    fun readAllData(): LiveData<List<Page>>
//
//    //TODO(delete unnecessary)
//    @Query("SELECT * FROM pages ORDER BY Id ASC LIMIT 10 OFFSET 10")
//    fun readSomeData() : LiveData<List<Page>>
//
//    @Query("SELECT * FROM pages WHERE id = :id")
//    suspend fun findByUserId(id: String?): Page?
}