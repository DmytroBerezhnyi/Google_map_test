package com.example.googlemaps.data.repository

import com.example.googlemaps.domain.model.Passage

interface PassageRepository {

    fun getPassages(): List<Passage>
}