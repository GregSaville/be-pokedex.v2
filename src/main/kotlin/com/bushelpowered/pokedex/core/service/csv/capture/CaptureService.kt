package com.bushelpowered.pokedex.core.service.csv.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.web.dto.capture.CapturedResponseDto
import com.bushelpowered.pokedex.core.egress.capture.CapturedPort
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import com.bushelpowered.pokedex.core.ingress.capture.CapturePokemonUseCase
import com.bushelpowered.pokedex.core.ingress.capture.FindTrainerPokemonUseCase
import org.springframework.stereotype.Service

@Service
class CaptureService(
    private val capturedPort: CapturedPort,
    private val trainerPort: TrainerPort
) : CapturePokemonUseCase, FindTrainerPokemonUseCase {

    override fun capturePokemon(pokeId: String, trainerId: Long): CapturedResponseDto? {
        val trainer = trainerPort.findTrainerById(trainerId)
        return if (trainer != null) {
            capturedPort.saveCaptured(Captured(trainer.id.toString() + "-" + pokeId, trainer.id, pokeId))
            CapturedResponseDto(
                trainer.id,
                trainer.name,
                trainer.email,
                trainer.capturedPokemon
            )
        } else null
    }

    override fun findCapturedPokemon(trainerId: Long): CapturedResponseDto? {
        val tmpTrainer = trainerPort.findTrainerById(trainerId)
        return if (tmpTrainer != null) {
            CapturedResponseDto(
                tmpTrainer.id,
                tmpTrainer.name,
                tmpTrainer.email,
                tmpTrainer.capturedPokemon
            )
        } else null
    }

}