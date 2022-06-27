package com.bushelpowered.pokedex.adapter.web.controller.capture

import com.bushelpowered.pokedex.adapter.web.dto.capture.CapturedResponseDto
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import com.bushelpowered.pokedex.core.service.capture.CaptureService
import com.bushelpowered.pokedex.adapter.web.controller.capture.utility.ValidateHelper
import com.bushelpowered.pokedex.core.ingress.capture.CapturePokemonUseCase
import com.bushelpowered.pokedex.core.ingress.capture.FindTrainerPokemonUseCase
import com.bushelpowered.pokedex.core.ingress.pokemon.FindPokemonUseCase
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
                    ResponseEntity.ok(captureUseCase.capturePokemon(pokeId, currentTrainer.id))
                } else ResponseEntity.badRequest().build()
            } else ResponseEntity.badRequest().build()
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long): ResponseEntity<CapturedResponseDto> {
        return if (trainerUseCase.getById(id) != null) {
            ResponseEntity.ok(findCapturedUseCase.findCapturedPokemon(id))
        } else (ResponseEntity.notFound().build())
    }

}