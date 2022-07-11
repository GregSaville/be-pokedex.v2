package com.bushelpowered.pokedex.adapter.web.controller.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.capture.CapturedResponseDto
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import com.bushelpowered.pokedex.adapter.web.controller.capture.utility.ValidateHelper
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel
import com.bushelpowered.pokedex.core.ingress.capture.CapturePokemonUseCase
import com.bushelpowered.pokedex.core.ingress.capture.FindTrainerPokemonUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class CapturedController(
    private val captureUseCase: CapturePokemonUseCase,
    private val findCapturedUseCase: FindTrainerPokemonUseCase,
    private val trainerUseCase: CrudTrainerUseCase
) {

    @PutMapping("/{id}/captured")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String): ResponseEntity<CapturedResponseDto> {
        val currentTrainer = trainerUseCase.getById(id)
        return if (currentTrainer != null) {
            if (ValidateHelper().isNumber(pokeId)) {
                if (pokeId.toInt() in 1..553) {
                    val newCapture = captureUseCase.capturePokemon(pokeId, currentTrainer.id)
                    ResponseEntity.ok(newCapture!!.toResponse(currentTrainer))
                } else ResponseEntity.badRequest().build()
            } else ResponseEntity.badRequest().build()
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long): ResponseEntity<CapturedResponseDto> {
        val trainer = trainerUseCase.getById(id)
        return if (trainer != null) {
            ResponseEntity.ok(findCapturedUseCase.findCapturedPokemon(trainer.id)!!.toResponse())
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{email}")
    fun getByEmail(@PathVariable email: String): ResponseEntity<CapturedResponseDto> {
        val currentTrainer = trainerUseCase.getByEmail(email)
        return if (currentTrainer != null) {
            ResponseEntity.ok(currentTrainer.toResponse())
        } else ResponseEntity.notFound().build()
    }

}

private fun Trainer.toResponse(): CapturedResponseDto {
    return CapturedResponseDto(this.id, this.name, this.email, this.capturedPokemon)
}

private fun TrainerModel.toResponse(): CapturedResponseDto {
    return CapturedResponseDto(this.id, this.name, this.email, this.capturedPokemon)
}

private fun Captured.toResponse(trainer: TrainerModel): CapturedResponseDto {
    return CapturedResponseDto(trainer.id, trainer.name, trainer.email, trainer.capturedPokemon)
}
