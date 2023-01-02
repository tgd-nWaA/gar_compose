package com.example.gar.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "pages")
data class Page (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var sequence: Int = 1,
    var lastTimeRepeat: Long = Calendar.getInstance().timeInMillis,
)

