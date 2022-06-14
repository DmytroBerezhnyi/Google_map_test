package com.example.googlemaps.app.ui

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.example.googlemaps.data.repository.RouteRepository
import com.example.googlemaps.domain.model.Place
import com.example.googlemaps.domain.repository.RawPlacesReader
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {

    val barcelona = LatLng(41.385064, 2.173403)
    val zaragoza = LatLng(41.648823, -0.889085)
    val madrid = LatLng(40.416775, -3.70379)

    fun getPlaces(resources: Resources): List<Place> = RawPlacesReader(resources).read()

    fun getPaths(routeRepository: RouteRepository): List<LatLng> {
        return routeRepository.getRoute(barcelona, madrid)
    }
}