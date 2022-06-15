package com.example.googlemaps.app.ui.passages_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemaps.R

class PassagesFragment : Fragment(R.layout.fragment_passages_history) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_passages)
        val adapter = PassageOverviewAdapter()
        recyclerView.adapter = adapter

    }
}