package com.bushelpowered.pokedex.adapter.persistence.dao.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.persistence.repository.capture.CapturedRepositoryPort
import com.bushelpowered.pokedex.core.egress.capture.CapturedPort
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class CapturedDao(private val captureRepo: CapturedRepositoryPort) : CapturedPort {
    override fun saveCaptured(captured: Captured): Boolean {
        return try {
            captureRepo.save(captured)
            true
        } catch (ex: Exception) {
            false
        }
    }
}