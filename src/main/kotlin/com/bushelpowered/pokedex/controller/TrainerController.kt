package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.web.bind.annotation.*

import java.util.*

@RestController
@RequestMapping("/api/trainers")
class TrainerController(val service: TrainerService){

    @GetMapping("")
    fun getAll() = service.getAllTrainers()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : Optional<Trainer> = service.getTrainerById(id)

    @RequestMapping(value= ["/"],method = [RequestMethod.GET])
    fun getTrainerByName(@RequestParam(value="name")name : String) : MutableIterable<Optional<Trainer>> = service.getTrainerByName(name)

    @PostMapping("")
    fun addTrainer(@RequestBody trainer: Trainer) : Trainer = service.addTrainer(trainer)

    @DeleteMapping("/{id}")
    fun removeTrainer(@PathVariable id: Long) = service.removeTrainer(getById(id))


}