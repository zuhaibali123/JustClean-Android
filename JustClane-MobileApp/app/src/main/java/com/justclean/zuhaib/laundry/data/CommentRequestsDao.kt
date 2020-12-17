package com.justclean.zuhaib.laundry.data

import androidx.room.*
import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.model.FavModel

@Dao
interface CommentRequestsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComment(request: CommentModel)

    @Query("SELECT * FROM CommentModel")
    fun getCommentList(): List<CommentModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFav(request: FavModel)

    @Query("SELECT * FROM FavModel")
    fun getFavModel(): List<FavModel>

    @Query("SELECT * FROM FavModel WHERE isSync = :isSych")
    fun getUnSyncFavItem(isSych: Boolean): List<FavModel>

    @Update
    fun updateFav(request: FavModel)
}