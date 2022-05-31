package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.service.entities.Pokemon
import com.bushelpowered.pokedex.service.entities.Trainer
import org.springframework.web.bind.annotation.*

import java.util.*

@RestController
class TrainerController(val service: TrainerService){

    @GetMapping("/trainers")
    fun index() = service.getAllTrainers()

    @GetMapping("/trainers/{id}")
    fun getById(@PathVariable id: Long) : Optional<Trainer> = service.getTrainerById(id)


    @PostMapping("/trainers")
    fun addTrainer(@RequestBody trainer: Trainer) : Trainer = service.addTrainer(trainer)

    @PutMapping("/trainers/{id}")
    fun capturePokemon(@PathVariable id: Long, @RequestBody pokeId: String) = service.addPokemon(pokeId, getById(id))

    @GetMapping("/trainers/{id}/captured")
    fun getTrainersPokemon(@PathVariable id: Long) : Any = service.findTrainersPokemon(getById(id))
}