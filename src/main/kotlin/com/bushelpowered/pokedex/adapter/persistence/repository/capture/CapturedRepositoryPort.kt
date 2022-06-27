package com.bushelpowered.pokedex.adapter.persistence.repository.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CapturedRepositoryPort : JpaRepository<Captured, String> {
    fun findEntriesByTrainerId(trainerId: Long): List<Captured>
}