package com.dyh.firistcode.jetpack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from BooK")
    fun loadAllBooks(): List<Book>
}