package com.example.googlemaps.domain.repository

import android.content.res.Resources
import com.example.googlemaps.R
import com.example.googlemaps.data.model.net.PlaceResponse
import com.example.googlemaps.data.model.net.Viewport
import com.example.googlemaps.data.repository.PlacesReader
import com.example.googlemaps.domain.model.Place
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

class RawPlacesReader(private val resources: Resources) : PlacesReader {

    private val gson = Gson()

    private val inputStream: InputStream
        get() = resources.openRawResource(R.raw.places)

    override fun read(): List<Place> {
        val typeToken = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        val placeResponses: List<PlaceResponse> = gson.fromJson(reader, typeToken)

        return placeResponses.map {
            Place(
                name = it.name,
                address = LatLng(it.geometry.location.lat, it.geometry.location.lng),
                northeast = LatLng(it.geometry.viewport.northeast.lat, it.geometry.viewport.northeast.lng),
                southwest = LatLng(it.geometry.viewport.southwest.lat, it.geometry.viewport.southwest.lng),
                rating = it.rating.toFloat()
            )
        }
    }
}