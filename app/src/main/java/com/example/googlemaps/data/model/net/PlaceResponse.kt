package com.example.googlemaps.data.model.net

data class PlaceResponse(
    val geometry: Geometry,
    val name: String,
    val rating: Double,
    val vicinity: String
)