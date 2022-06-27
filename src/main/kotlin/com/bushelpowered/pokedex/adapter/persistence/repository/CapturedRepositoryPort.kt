package com.bushelpowered.pokedex.adapter.persistence.repository

import com.bushelpowered.pokedex.adapter.persistence.entities.Captured
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CapturedRepositoryPort : JpaRepository<Captured, String> {
    fun findEntriesByTrainerId(trainerId: Long): List<Captured>
}