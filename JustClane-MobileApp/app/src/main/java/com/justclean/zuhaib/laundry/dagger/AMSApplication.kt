package com.justclean.zuhaib.laundry.dagger

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import com.justclean.zuhaib.laundry.dagger.components.DaggerApiComponent
//import com.tatweer.mhussain.assetmanagementsystem.dagger.components.DaggerAppComponent
import com.justclean.zuhaib.laundry.dagger.modules.AppModule
import com.justclean.zuhaib.laundry.data.RoomSupport
import dagger.android.*
import javax.inject.Inject

class AMSApplication : Application() {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    lateinit var preferences: SharedPreferences


     override fun onCreate() {
        super.onCreate()

         preferences = getSharedPreferences("AMSPref", Context.MODE_PRIVATE)
         RoomSupport.Companion.createInstance(applicationContext)
         setNewRetrofit()

    }

    fun setNewRetrofit(){
        DaggerApiComponent
                .builder()
                .appModule(AppModule(this, preferences))
                .build()

    }



}