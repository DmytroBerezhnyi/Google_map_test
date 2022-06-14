package com.example.googlemaps.data.repository

import com.google.android.gms.maps.model.LatLng

interface RouteRepository {

    fun getRoute(from: LatLng, to: LatLng): List<LatLng>
}