package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class CapturedController(private val TrainerService : TrainerService) {

    @PutMapping("/{id}")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String) = TrainerService.addPokemon(pokeId, TrainerService.getTrainerById(id))

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long) : List<Pokemon> {
        return TrainerService.findTrainersPokemon(TrainerService.getTrainerById(id))
    }

    @DeleteMapping("/{id}/captured")
    fun clearCapturedPokemon(@PathVariable id: Long) = TrainerService.clearTrainersPokemon(TrainerService.getTrainerById(id))

}