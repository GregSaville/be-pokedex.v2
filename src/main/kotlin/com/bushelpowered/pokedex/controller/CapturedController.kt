package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class CapturedController(private val TrainerService : TrainerService) {

    @PutMapping("/{id}/captured")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String) : ResponseEntity<Trainer> {
        val currentTrainer = TrainerService.getTrainerById(id)
        return if(currentTrainer != null){
            ResponseEntity.ok(TrainerService.addPokemon(pokeId, currentTrainer))
        }else(ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/captured")
    fun getCapturedPokemon(@PathVariable id: Long) : ResponseEntity<List<Pokemon>> {
        val currentTrainer = TrainerService.getTrainerById(id)
        return if(currentTrainer != null){
            ResponseEntity.ok(TrainerService.findTrainersPokemon(currentTrainer))
        }else(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}/captured")
    fun clearCapturedPokemon(@PathVariable id: Long) : ResponseEntity<Trainer> {
        val currentTrainer = TrainerService.getTrainerById(id)
        return if(currentTrainer != null){
            ResponseEntity.accepted().build()
        }else(ResponseEntity.notFound().build())
    }

}