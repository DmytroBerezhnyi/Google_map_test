package com.example.googlemaps.domain.repository

import com.example.googlemaps.data.repository.PassageRepository
import com.example.googlemaps.domain.model.Passage

class MockPassageRepository : PassageRepository {

    override fun getPassages(): List<Passage> {
        return listOf(
            Passage(
                id = 0,
                carName = "Дракон (Часть 1)",
                generalLoad = 36,
                totalLength = 67.0,
                totalOutlets = 17
            ),
            Passage(
                id = 1,
                carName = "Дракон (Часть 2)",
                generalLoad = 20,
                totalLength = 34.0,
                totalOutlets = 6
            )
        )
    }
}