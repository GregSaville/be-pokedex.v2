package com.bushelpowered.pokedex.adapter.persistence.repository.capture

import com.bushelpowered.pokedex.adapter.persistence.dao.capture.CapturedDao
import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.core.egress.capture.CapturedPort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class CapturedRepositoryPort(private val capturedDao: CapturedDao) : CapturedPort {
    override fun saveCaptured(captured: Captured): Boolean {
        return try {
            capturedDao.save(captured)
            true
        } catch (ex: Exception) {
            false
        }
    }
}