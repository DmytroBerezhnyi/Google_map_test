package com.example.googlemaps.app.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.googlemaps.R
import com.example.googlemaps.app.App
import com.example.googlemaps.app.BitmapHelper
import com.example.googlemaps.app.adapter.InfoWindowAdapter
import com.example.googlemaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding

    private val vm: MainViewModel by viewModel()

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.purple_500)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_baseline_directions_bike_24, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(::onMapReady)
    }

    private fun onMapReady(googleMap: GoogleMap) {
        showRoute(googleMap)
        //test(googleMap)
    }

    private fun test(googleMap: GoogleMap) {
        googleMap.setInfoWindowAdapter(InfoWindowAdapter(this))

        val markers = vm.getPlaces(resources).mapNotNull { place ->
            googleMap.addMarker(
                MarkerOptions().title(place.name).position(place.address).icon(bicycleIcon)
            )?.apply {
                this.tag = place
            }
        }

        googleMap.setOnCameraMoveListener {
            Log.d(
                "TAAAAG", googleMap.cameraPosition.zoom.toString()
            )
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-35.016, 143.321), 1f))

        val polyline = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(-35.016, 143.321),
                    LatLng(-34.747, 145.592),
                    LatLng(-34.364, 147.891),
                    LatLng(-33.501, 150.217),
                    LatLng(-32.306, 149.248),
                    LatLng(-32.491, 147.309)
                )
        )


        polyline.jointType = JointType.ROUND
        polyline.startCap = CustomCap(bicycleIcon, 16F)
        polyline.endCap = CustomCap(bicycleIcon, 16F)
        polyline.tag = "A"

        //addSydneyMarker()
    }

    private fun showRoute(googleMap: GoogleMap) {
        googleMap.addMarker(MarkerOptions().position(vm.barcelona).title("Marker in Barcelona"))
        googleMap.addMarker(MarkerOptions().position(vm.madrid).title("Marker in Madrid"))

        val paths = vm.getPaths()

        //Draw the polyline
        if (paths.isNotEmpty()) {
            val opts = PolylineOptions().addAll(paths).color(Color.BLUE).width(5f)
            googleMap.addPolyline(opts)
        }
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vm.zaragoza, 6f))
    }
}