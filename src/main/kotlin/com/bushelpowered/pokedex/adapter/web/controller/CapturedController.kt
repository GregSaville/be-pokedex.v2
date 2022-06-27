package com.bushelpowered.pokedex.adapter.web.controller

import com.bushelpowered.pokedex.adapter.web.dto.CapturedResponseDTO
import com.bushelpowered.pokedex.core.ingress.CrudTrainerUseCase
import com.bushelpowered.pokedex.core.service.CaptureService
import com.bushelpowered.pokedex.core.utilites.ValidateHelper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class CapturedController(
    private val captureService: CaptureService,
    private val trainerUseCase: CrudTrainerUseCase
) {

    @PutMapping("/{id}/captured")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String): ResponseEntity<CapturedResponseDTO> {
        val currentTrainer = trainerUseCase.getById(id)
        return if (currentTrainer != null) {
            if (ValidateHelper().isNumber(pokeId)) {
                if (pokeId.toInt() in 1..553) {
                    ResponseEntity.ok(captureService.capturePokemon(pokeId, currentTrainer.id))
                } else ResponseEntity.badRequest().build()
            } else ResponseEntity.badRequest().build()
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long): ResponseEntity<CapturedResponseDTO> {
        return if (trainerUseCase.getById(id) != null) {
            ResponseEntity.ok(captureService.findCapturedPokemon(id))
        } else (ResponseEntity.notFound().build())
    }

}