package com.example.googlemaps.domain.model

import com.google.android.gms.maps.model.LatLng

data class Place(
    val name: String,
    val address: LatLng,
    val northeast: LatLng,
    val southwest: LatLng,
    val rating: Float
)