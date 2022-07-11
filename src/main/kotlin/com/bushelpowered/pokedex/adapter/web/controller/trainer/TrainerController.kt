package com.bushelpowered.pokedex.adapter.web.controller.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerResponseDto
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class TrainerController(private val trainerUseCase: CrudTrainerUseCase) {

    @GetMapping("/api/trainers")
    fun getAll(): ResponseEntity<List<TrainerResponseDto>> {
        val result = trainerUseCase.getAllTrainers()
        val responseList = mutableListOf<TrainerResponseDto>()
        return if (result.isNotEmpty()) {
            result.forEach {
                responseList.add(it.toResponse())
            }
            ResponseEntity.ok(responseList)
        } else ResponseEntity.notFound().build()
    }



    @PostMapping("/signup")
    fun addTrainer(@RequestBody trainer: TrainerRequestDto): ResponseEntity<TrainerResponseDto> {
        return if (trainerUseCase.addTrainer(trainer)) {
            ResponseEntity.ok().build()
        } else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/api/trainers/{id}")
    fun removeTrainer(@PathVariable id: Long): ResponseEntity<TrainerResponseDto> {
        val currentTrainer = trainerUseCase.getById(id)
        return if (currentTrainer != null) {
            if (trainerUseCase.removeTrainer(currentTrainer.id)) {
                ResponseEntity.accepted().build()
            } else ResponseEntity.badRequest().build()
        } else ResponseEntity.notFound().build()
    }

}

private fun TrainerModel.toResponse(): TrainerResponseDto {
    return TrainerResponseDto(this.id, this.name, this.email)
}
