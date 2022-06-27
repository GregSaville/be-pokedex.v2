package com.bushelpowered.pokedex.adapter.persistence.dao

import com.bushelpowered.pokedex.adapter.persistence.repository.TrainerRepository
import com.bushelpowered.pokedex.adapter.web.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.adapter.web.dto.TrainerResponseDTO
import com.bushelpowered.pokedex.core.egress.TrainerPort
import com.bushelpowered.pokedex.adapter.persistence.entities.Trainer
import com.bushelpowered.pokedex.adapter.persistence.repository.CapturedRepositoryPort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.security.SecureRandom

@Component
class TrainerDao(private val repo: TrainerRepository, private val capturedRepository: CapturedRepositoryPort) : TrainerPort {

    val encoder = BCryptPasswordEncoder(10, SecureRandom())

    override fun findAllTrainers(): List<Trainer> {
        return repo.findAll().toList()
    }

    override fun findTrainerById(id: Long): Trainer? {
        val tmpTrainer = repo.findById(id)
        return if (tmpTrainer.isPresent) {
           tmpTrainer.get()
        } else null
    }

    override fun findTrainerByEmail(email: String): Trainer? {
        val tmpTrainer = repo.findTrainerByEmail(email)
        return if (tmpTrainer != null) {
            tmpTrainer!!
        } else null
    }

    override fun addTrainer(trainer: TrainerRequestDTO): Boolean {
        return try {
            repo.save(
                Trainer(
                    findAllTrainers().size.toLong() + 1,
                    trainer.name,
                    trainer.email,
                    encoder.encode(trainer.password),
                    emptyList()
                )
            )
            true
        }catch (ex : Exception){
            false
        }
    }

    override fun removeTrainer(id: Long): Boolean {
        return try {
            val tmpTrainer = repo.findById(id)
            repo.delete(tmpTrainer.get())
            capturedRepository.findEntriesByTrainerId(id).forEach {
                capturedRepository.delete(it)
            }
            true
        }catch (ex : Exception){
            false
        }
    }
}