package com.bushelpowered.pokedex.core.service

import com.bushelpowered.pokedex.adapter.persistence.entities.Captured
import com.bushelpowered.pokedex.adapter.web.dto.CapturedResponseDTO
import com.bushelpowered.pokedex.core.egress.CapturedPort
import com.bushelpowered.pokedex.core.egress.TrainerPort
import com.bushelpowered.pokedex.core.ingress.capture.CapturePokemonUseCase
import com.bushelpowered.pokedex.core.ingress.capture.FindTrainerPokemonUseCase
import org.springframework.stereotype.Service

@Service
class CaptureService(
    private val capturedPort: CapturedPort,
    private val trainerPort: TrainerPort) : CapturePokemonUseCase, FindTrainerPokemonUseCase {

    override fun capturePokemon(pokeId: String, trainerId: Long): CapturedResponseDTO? {
        val trainer = trainerPort.findTrainerById(trainerId)
        return if (trainer != null) {
            capturedPort.saveCaptured(Captured(trainer.id.toString() + "-" + pokeId, trainer.id, pokeId))
            CapturedResponseDTO(
                trainer.id,
                trainer.name,
                trainer.email,
                trainer.capturedPokemon
            )
        } else null
    }

    override fun findCapturedPokemon(trainerId: Long): CapturedResponseDTO? {
        val tmpTrainer = trainerPort.findTrainerById(trainerId)
        return if (tmpTrainer != null) {
            CapturedResponseDTO(
                tmpTrainer.id,
                tmpTrainer.name,
                tmpTrainer.email,
                tmpTrainer.capturedPokemon
            )
        } else null
    }

}