package com.example.googlemaps.domain.model

data class Passage(
    val id: Int,
    val carName: String,
    val generalLoad: Int,
    val totalLength: Double, //km
    val totalOutlets: Int
)