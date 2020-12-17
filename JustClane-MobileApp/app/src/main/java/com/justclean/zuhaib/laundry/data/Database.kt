package com.justclean.zuhaib.laundry.data

import androidx.room.Database
import androidx.room.RoomDatabase

import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.model.FavModel

@Database(entities = arrayOf(CommentModel::class, FavModel::class), version = 1, exportSchema = false)
public abstract class Database: RoomDatabase() {

    abstract fun getCommentListDao(): CommentRequestsDao

}