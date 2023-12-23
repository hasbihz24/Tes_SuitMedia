package com.example.suitmedia_tes.di

import com.example.suitmedia_tes.Api.ApiClient
import com.example.suitmedia_tes.repos.MainRepository
import com.example.suitmedia_tes.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val dataModule
        get() = module {
            single { ApiClient.instance }

            factory { MainRepository() }
        }

    val uiModule
        get() = module {
            viewModel { MainViewModel(get()) }
        }
}