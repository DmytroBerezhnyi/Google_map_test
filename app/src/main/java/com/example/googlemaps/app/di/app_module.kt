package com.example.googlemaps.app.di

import com.example.googlemaps.app.ui.MainViewModel
import com.example.googlemaps.data.repository.PassageRepository
import com.example.googlemaps.data.repository.RouteRepository
import com.example.googlemaps.domain.repository.MockPassageRepository
import com.example.googlemaps.domain.repository.RouteRepositoryImpl
import com.google.maps.GeoApiContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single<GeoApiContext> {
        GeoApiContext.Builder()
            .apiKey("AIzaSyAPDbocY5Wb7fM1cVe_VCNBjHHg-ldFuiU")
            .build()
    }

    single<RouteRepository> { RouteRepositoryImpl(get()) }
    single<PassageRepository> { MockPassageRepository() }

    viewModel { MainViewModel(get()) }
}