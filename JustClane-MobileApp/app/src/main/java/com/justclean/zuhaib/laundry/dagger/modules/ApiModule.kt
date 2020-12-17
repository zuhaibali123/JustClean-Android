package com.justclean.zuhaib.laundry.dagger.modules

import com.justclean.zuhaib.laundry.model.PostCommentApi
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private  val BASE_URL  = "https://jsonplaceholder.typicode.com"
    @Provides
    fun provideCountriesApi(): PostCommentApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PostCommentApi::class.java)
    }

    @Provides
    fun provideCommentService(): commentListService {
        return commentListService()
    }

}