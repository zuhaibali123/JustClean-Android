package com.justclean.zuhaib.laundry.viewmodel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.justclean.zuhaib.laundry.dagger.components.DaggerApiComponent
import com.justclean.zuhaib.laundry.dagger.modules.commentListService

import com.justclean.zuhaib.laundry.data.RoomSupport
import com.justclean.zuhaib.laundry.model.CommentModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListViewModel : ViewModel() {

    @Inject
    lateinit var mostViewedService: commentListService
    private val disposable = CompositeDisposable()

    val postCommentList = MutableLiveData<List<CommentModel>>()
    val postCommentLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    //initializing the necessary components and classes
    init {
        DaggerApiComponent.create().inject(this)
    }



     fun fetchCommentData(){
        loading.value = true
        disposable.add(mostViewedService.getPostComment().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<CommentModel>> (){
                    override fun onSuccess(data: List<CommentModel>) {
                        postCommentList.value = data
                        postCommentLoadError.value = false
                        data!!.forEachIndexed { index, commentModel ->
                            RoomSupport.getInstance().database.getCommentListDao().addComment(commentModel)
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        postCommentLoadError.value = true
                        loading.value = false
                    }



                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}