package com.bushelpowered.pokedex.adapter.persistence.dao

import com.bushelpowered.pokedex.adapter.persistence.entities.Captured
import com.bushelpowered.pokedex.adapter.persistence.repository.CapturedRepositoryPort
import com.bushelpowered.pokedex.adapter.web.dto.CapturedResponseDTO
import com.bushelpowered.pokedex.core.egress.CapturedPort
import org.springframework.stereotype.Component

@Component
class CapturedDao(private val captureRepo: CapturedRepositoryPort) : CapturedPort {
    override fun saveCaptured(captured: Captured): Boolean {
        return try{
            captureRepo.save(captured)
            true
        }catch (ex: Exception){
            false
        }
    }

}