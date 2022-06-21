package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.dto.TrainerResponseDTO
import com.bushelpowered.pokedex.entities.Trainer
import com.bushelpowered.pokedex.repositories.CapturedRepository
import com.bushelpowered.pokedex.repositories.TrainerRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class TrainerService(
    private val trainerRepository: TrainerRepository,
    private val capturedRepository: CapturedRepository
) {

    val encoder = BCryptPasswordEncoder(10, SecureRandom())

    fun getAllTrainers(): List<TrainerResponseDTO> {
        val tmpMutList = mutableListOf<TrainerResponseDTO>()
        trainerRepository.findAll().forEach {
            tmpMutList.add(
                TrainerResponseDTO(
                    it.id,
                    it.name,
                    it.email
                )
            )
        }
        return tmpMutList.toList()
    }

    fun getTrainerById(trainerId: Long): TrainerResponseDTO? {
        val tmpTrainer = trainerRepository.findById(trainerId)
        return if (tmpTrainer.isPresent) {
            TrainerResponseDTO(
                tmpTrainer.get().id,
                tmpTrainer.get().name,
                tmpTrainer.get().email
            )
        } else null
    }

    fun getTrainerByEmail(email: String): TrainerResponseDTO? {
        val tmpTrainer = trainerRepository.findTrainerByEmail(email)
        return if (tmpTrainer != null) {
            TrainerResponseDTO(
                tmpTrainer.id,
                tmpTrainer.name,
                tmpTrainer.email
            )
        } else null
    }

    fun addTrainer(trainer: TrainerRequestDTO): ResponseEntity<TrainerResponseDTO> {
        trainerRepository.save(
            Trainer(
                getAllTrainers().size.toLong() + 1,
                trainer.name,
                trainer.email,
                encoder.encode(trainer.password),
                emptyList()
            )
        )
        return ResponseEntity.accepted().build()
    }

    fun removeTrainer(id: Long) {
        val tmpTrainer = trainerRepository.findById(id)
        trainerRepository.delete(tmpTrainer.get())
        capturedRepository.findEntriesByTrainerId(id).forEach {
            capturedRepository.delete(it)
        }
    }

}



