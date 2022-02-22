package com.dyh.firistcode.contentprovider

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf

class MoniTest : AppCompatActivity() {
    var bookId: String? = null

    fun add() {
        var uri = Uri.parse("content://com.dyh.firistcode.contentprovider.provider/book")
        val values = contentValuesOf("name" to "A Clash of Kings",
            "author" to "George Martin", "pages" to 1040, "price" to 22.85)
        val newUri = contentResolver.insert(uri, values)
        bookId = newUri?.pathSegments?.get(1)
    }

    fun query() {
        var uri = Uri.parse("content://com.dyh.firistcode.contentprovider.provider/book")
        contentResolver.query(uri, null, null, null, null)?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndex("name"))
                val author = getString(getColumnIndex("author"))
                val pages = getInt(getColumnIndex("pages"))
                val price = getDouble(getColumnIndex("price"))
            }
            close()
        }
    }

    fun update() {
        bookId?.let {
            var uri = Uri.parse("content://com.dyh.firistcode.contentprovider.provider/book/$it")
            val values = contentValuesOf("name" to "A Clash of Kings", "pages" to 1040, "price" to 24.85)
            contentResolver.update(uri, values, null, null)
        }
    }

    fun delete() {
        bookId?.let {
            var uri = Uri.parse("content://com.dyh.firistcode.contentprovider.provider/book/$it")
            contentResolver.delete(uri, null, null)
        }
    }
}