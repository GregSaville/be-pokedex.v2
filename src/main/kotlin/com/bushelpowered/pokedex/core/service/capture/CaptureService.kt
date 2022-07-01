package com.bushelpowered.pokedex.core.service.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
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

    override fun capturePokemon(pokeId: String, trainerId: Long): Captured? {
        val trainer = trainerPort.findTrainerById(trainerId)
        return if (trainer != null) {
            val newCapture = Captured(trainer.id.toString() + "-" + pokeId, trainer.id, pokeId)
            capturedPort.saveCaptured(newCapture)
            newCapture
        } else null
    }

    override fun findCapturedPokemon(trainerId: Long): Trainer? {
        val tmpTrainer = trainerPort.findTrainerById(trainerId)
        return if (tmpTrainer != null) {
            return tmpTrainer
        } else null
    }

}