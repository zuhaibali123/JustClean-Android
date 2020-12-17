package com.justclean.zuhaib.laundry.dagger.components

import com.justclean.zuhaib.laundry.dagger.modules.ApiModule
import com.justclean.zuhaib.laundry.dagger.modules.AppModule
import com.justclean.zuhaib.laundry.viewmodel.ListViewModel
import com.justclean.zuhaib.laundry.dagger.modules.commentListService
import dagger.Component

@Component(modules = [ApiModule::class, AppModule::class])
interface ApiComponent {

    fun inject(service: commentListService)

    fun inject(viewModel: ListViewModel)

}