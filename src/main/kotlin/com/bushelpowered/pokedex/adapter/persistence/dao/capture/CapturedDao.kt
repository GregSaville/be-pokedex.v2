package com.bushelpowered.pokedex.adapter.persistence.dao.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CapturedDao : JpaRepository<Captured, String> {
    fun findEntriesByTrainerId(id: Long): List<Captured>
}