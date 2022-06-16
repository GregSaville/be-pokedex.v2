package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.CapturedResponseDTO
import com.bushelpowered.pokedex.entities.Captured
import com.bushelpowered.pokedex.repositories.CapturedRepository
import com.bushelpowered.pokedex.repositories.TrainerRepository
import org.springframework.stereotype.Service

@Service
class CaptureService(private val trainerRepository: TrainerRepository, private val capturedRepository: CapturedRepository) {

    fun addPokemon(pokeId: String, trainerId: Long): CapturedResponseDTO? {
        val trainer = trainerRepository.findById(trainerId)
        return if(trainer.isPresent){
            capturedRepository.save(Captured(trainer.get().id.toString()+"-"+pokeId, trainer.get().id,pokeId))
            CapturedResponseDTO(trainer.get().id, trainer.get().name, trainer.get().email, trainer.get().capturedPokemon)
        } else null
    }

    fun findTrainersPokemon(trainerId: Long): CapturedResponseDTO? {
        val tmpTrainer = trainerRepository.findById(trainerId)
        return if(tmpTrainer.isPresent){
            CapturedResponseDTO(
                tmpTrainer.get().id,
                tmpTrainer.get().name,
                tmpTrainer.get().email,
                tmpTrainer.get().capturedPokemon
            )
        }else null
    }

}