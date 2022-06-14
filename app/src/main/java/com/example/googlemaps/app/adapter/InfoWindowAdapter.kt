package com.example.googlemaps.app.adapter

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.googlemaps.R
import com.example.googlemaps.domain.model.Place
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class InfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View {
        val view = LayoutInflater.from(context).inflate(R.layout.marker_info_content, null)

        val place = marker.tag as? Place ?: return view

        val geocoder = Geocoder(context)
        val address = geocoder.getFromLocation(place.address.latitude, place.address.longitude, 1)

        view.findViewById<TextView>(R.id.text_view_title).text = place.name
        view.findViewById<TextView>(R.id.text_view_address).text =
            address.firstOrNull()?.getAddressLine(0)
        view.findViewById<TextView>(R.id.text_view_rating).text =
            "Rating: %.2f".format(place.rating)

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to indicate that the
        // default window (white bubble) should be used
        return null
    }
}