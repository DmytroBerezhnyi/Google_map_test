package com.example.googlemaps.data.repository

import com.example.googlemaps.domain.model.Place

interface PlacesReader {

    fun read(): List<Place>
}