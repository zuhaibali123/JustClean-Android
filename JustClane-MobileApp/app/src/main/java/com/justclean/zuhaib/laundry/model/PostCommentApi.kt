package com.justclean.zuhaib.laundry.model

import io.reactivex.Single
import retrofit2.http.GET

interface PostCommentApi {

    @GET("/posts")
    fun getPostCommentList():Single<List<CommentModel>>

}