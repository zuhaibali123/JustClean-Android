package com.justclean.zuhaib.laundry.dagger.modules


import com.justclean.zuhaib.laundry.dagger.components.DaggerApiComponent
import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.model.PostCommentApi
import io.reactivex.Single
import javax.inject.Inject

class commentListService {

    @Inject
    lateinit var api: PostCommentApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPostComment():Single<List<CommentModel>>{
        return api.getPostCommentList()
    }
}