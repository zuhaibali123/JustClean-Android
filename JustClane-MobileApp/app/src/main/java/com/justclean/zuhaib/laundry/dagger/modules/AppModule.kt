package com.justclean.zuhaib.laundry.dagger.modules

import android.app.Application
import androidx.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.justclean.zuhaib.laundry.data.Database

import com.justclean.zuhaib.laundry.data.CommentRequestsDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: Application, private val preferences: SharedPreferences) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideAssetsDatabase(app: Application): Database =
            Room.databaseBuilder(app, Database::class.java, "assets_db").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRequestsDao(db: Database): CommentRequestsDao = db.getCommentListDao()



}