package com.bushelpowered.pokedex.adapter.persistence.dao.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.persistence.repository.capture.CapturedRepositoryPort
import com.bushelpowered.pokedex.core.egress.capture.CapturedPort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
interface CapturedDao: JpaRepository<Captured, String>{
    fun findEntriesByTrainerId(id : Long) : List<Captured>
}