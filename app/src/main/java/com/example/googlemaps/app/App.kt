package com.example.googlemaps.app

import android.app.Application
import com.example.googlemaps.data.repository.RouteRepository
import com.example.googlemaps.domain.repository.RouteRepositoryImpl
import com.google.maps.GeoApiContext

class App : Application() {

    private val geoApiContext: GeoApiContext by lazy {
        GeoApiContext.Builder()
            .apiKey("AIzaSyAPDbocY5Wb7fM1cVe_VCNBjHHg-ldFuiU")
            .build()
    }

    val routeRepository: RouteRepository by lazy {
        RouteRepositoryImpl(geoApiContext)
    }
}