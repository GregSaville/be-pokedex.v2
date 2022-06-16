package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.dto.TrainerResponseDTO
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotNull
import org.valiktor.validate

@RestController
@RequestMapping("/api/trainers")
class TrainerController(private val trainerService: TrainerService){

    @GetMapping("")
    fun getAll(): ResponseEntity<List<TrainerResponseDTO>> {
        return if(trainerService.getAllTrainers().isNotEmpty()){
            ResponseEntity.ok(trainerService.getAllTrainers())
        }else ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<TrainerResponseDTO> {
        val currentTrainer = trainerService.getTrainerById(id)
        return if(currentTrainer != null){
            ResponseEntity.ok(currentTrainer)
        }else ResponseEntity.notFound().build()
    }

    @PostMapping("")
    fun addTrainer(@RequestBody trainer: TrainerRequestDTO) : ResponseEntity<TrainerResponseDTO> {
        return if(trainerService.getTrainerByEmail(trainer.email) == null){
            try{
                validate(TrainerRequestDTO(trainer.name, trainer.email, trainer.password)){
                    validate(TrainerRequestDTO::name).isNotNull().hasSize(min=2)
                    validate(TrainerRequestDTO::email).isEmail()
                    validate(TrainerRequestDTO::password).hasSize(min=6)
                }
                trainerService.addTrainer(trainer)
            } catch (ex: ConstraintViolationException){
                ex.constraintViolations
                    .map { "${it.property}: ${it.constraint.name}" }
                    .forEach(::println)
                ResponseEntity.badRequest().build()
            }
        }else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun removeTrainer(@PathVariable id: Long) : ResponseEntity<TrainerResponseDTO> {
        val currentTrainer = trainerService.getTrainerById(id)
        return if(currentTrainer != null) {
            trainerService.removeTrainer(currentTrainer.id)
            ResponseEntity.accepted().build()
        }else ResponseEntity.notFound().build()
    }

}