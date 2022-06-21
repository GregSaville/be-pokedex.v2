package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.CapturedResponseDTO
import com.bushelpowered.pokedex.service.CaptureService
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utilites.ValidateHelper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class CapturedController(private val captureService: CaptureService, private val trainerService: TrainerService) {

    @PutMapping("/{id}/captured")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String): ResponseEntity<CapturedResponseDTO> {
        val currentTrainer = trainerService.getTrainerById(id)
        return if (currentTrainer != null) {
            if (ValidateHelper().isNumber(pokeId)) {
                if (pokeId.toInt() in 1..553) {
                    ResponseEntity.ok(captureService.addPokemon(pokeId, currentTrainer.id))
                } else ResponseEntity.badRequest().build()
            } else ResponseEntity.badRequest().build()
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long): ResponseEntity<CapturedResponseDTO> {
        return if (trainerService.getTrainerById(id) != null) {
            ResponseEntity.ok(captureService.findTrainersPokemon(id))
        } else (ResponseEntity.notFound().build())
    }

}