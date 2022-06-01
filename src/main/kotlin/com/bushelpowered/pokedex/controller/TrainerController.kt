package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.web.bind.annotation.*

import java.util.*

@RestController
@RequestMapping("/api")
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
    fun getTrainersPokemon(@PathVariable id: Long) : MutableIterable<Pokemon> = service.findTrainersPokemon(getById(id))
}