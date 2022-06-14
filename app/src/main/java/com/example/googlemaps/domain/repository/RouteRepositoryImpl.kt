package com.example.googlemaps.domain.repository

import android.util.Log
import com.example.googlemaps.data.repository.RouteRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.DirectionsResult
import com.google.maps.model.DirectionsRoute
import com.google.maps.model.DirectionsStep

class RouteRepositoryImpl(private val geoApiContext: GeoApiContext) : RouteRepository {

    override fun getRoute(from: LatLng, to: LatLng): List<LatLng> {
        val directionsApiRequest = DirectionsApi.getDirections(
            geoApiContext,
            from.toCoordinates(),
            to.toCoordinates()
        )

        val paths = mutableListOf<LatLng>()

        try {
            val res: DirectionsResult = directionsApiRequest.await()
            val route: DirectionsRoute? = res.routes.firstOrNull()

            route?.legs?.forEach { directionsLeg ->
                directionsLeg.steps?.forEach { step ->
                    if (step.steps.isNullOrEmpty()) {
                        paths.addAll(getAllSteps(step))
                    } else {
                        step.steps.forEach { subStep ->
                            paths.addAll(getAllSteps(subStep))
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("TAG", "Something went wrong", ex)
        }

        return paths
    }

    //Decode polyline and add points to list of route coordinates
    private fun getAllSteps(step: DirectionsStep): List<LatLng> {
        val coordinates = step.polyline.decodePath()
        return coordinates.map { coordinate ->
            LatLng(coordinate.lat, coordinate.lng)
        }
    }

    private fun LatLng.toCoordinates(): String {
        return "$latitude,$longitude"
    }
}