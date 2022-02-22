package com.dyh.firistcode.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyProvider : ContentProvider() {

    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init {
        uriMatcher.addURI("com.dyh.firistcode.provider", "table1", table1Dir)
        uriMatcher.addURI("com.dyh.firistcode.provider", "table1/#", table1Item)
        uriMatcher.addURI("com.dyh.firistcode.provider", "table2", table2Dir)
        uriMatcher.addURI("com.dyh.firistcode.provider", "table2/#", table2Item)
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {
                //查询table1的所有数据
            }
            table1Item -> {
                //查询table1中的单条数据
            }
            table2Dir -> {
                //查询table2的所有数据
            }
            table2Item -> {
                //查询table2中的单条数据
            }
        }

        return null
    }

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        //获取uri对象对应的MIME类型
            //1. 必须以vnd开头
            //2.如果uri以路径结尾则后接android.cursor.dir/;如果以id结尾，则接android.cursor.item/
            //3.最后接上vnd.<authority>.<path>
        table1Dir -> "vnd.android.cursor.dir/vnd.com.dyh.firistcode.provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.dyh.firistcode.provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.dyh.firistcode.provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.dyh.firistcode.provider.table2"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}