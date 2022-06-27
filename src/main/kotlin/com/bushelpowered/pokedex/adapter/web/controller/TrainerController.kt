package com.bushelpowered.pokedex.adapter.web.controller

import com.bushelpowered.pokedex.adapter.web.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.adapter.web.dto.TrainerResponseDTO
import com.bushelpowered.pokedex.core.ingress.CrudTrainerUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/trainers")
class TrainerController(private val trainerUseCase: CrudTrainerUseCase) {

    @GetMapping("")
    fun getAll(): ResponseEntity<List<TrainerResponseDTO>> {
        return if (trainerUseCase.getAllTrainers().isNotEmpty()) {
            ResponseEntity.ok(trainerUseCase.getAllTrainers())
        } else ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TrainerResponseDTO> {
        val currentTrainer = trainerUseCase.getById(id)
        return if (currentTrainer != null) {
            ResponseEntity.ok(currentTrainer)
        } else ResponseEntity.notFound().build()
    }

    @PostMapping("")
    fun addTrainer(@RequestBody trainer: TrainerRequestDTO): ResponseEntity<TrainerResponseDTO> {
        return if(trainerUseCase.addTrainer(trainer)){
            ResponseEntity.ok().build()
        }else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun removeTrainer(@PathVariable id: Long): ResponseEntity<TrainerResponseDTO> {
        val currentTrainer = trainerUseCase.getById(id)
        return if (currentTrainer != null) {
            if(trainerUseCase.removeTrainer(currentTrainer.id)){
                ResponseEntity.accepted().build()
            }else ResponseEntity.badRequest().build()
        } else ResponseEntity.notFound().build()
    }

}