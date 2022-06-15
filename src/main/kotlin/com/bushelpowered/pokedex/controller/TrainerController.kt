package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/trainers")
class TrainerController(val service: TrainerService){

    @GetMapping("")
    fun getAll() = service.getAllTrainers()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<Trainer> {
        val currentTrainer = service.getTrainerById(id)
        return if(currentTrainer != null){
            ResponseEntity.ok(currentTrainer)
        }else ResponseEntity.notFound().build()
    }

    @RequestMapping(value= ["/"],method = [RequestMethod.GET])
    fun getTrainerByName(@RequestParam(value="name")name : String) : ResponseEntity<List<Trainer>> {
        val currentTrainers = service.getTrainerByName(name)
        return if(currentTrainers.isNotEmpty()){
            ResponseEntity.ok(currentTrainers)
        }else ResponseEntity.notFound().build()
    }

//    @PostMapping("")
//    fun addTrainer(@RequestBody trainer: TrainerRequestDTO) : ResponseEntity<Trainer> = ResponseEntity.ok(service.addTrainer(trainer))

    @DeleteMapping("/{id}")
    fun removeTrainer(@PathVariable id: Long) : ResponseEntity<Trainer> {
        val currentTrainer = service.getTrainerById(id)
        return if(currentTrainer != null) {
            service.removeTrainer(currentTrainer)
            ResponseEntity.accepted().build()
        }else ResponseEntity.notFound().build()
    }


}