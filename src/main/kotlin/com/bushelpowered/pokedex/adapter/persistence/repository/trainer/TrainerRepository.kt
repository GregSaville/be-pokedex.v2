package com.bushelpowered.pokedex.adapter.persistence.repository.trainer

import com.bushelpowered.pokedex.adapter.persistence.dao.capture.CapturedDao
import com.bushelpowered.pokedex.adapter.persistence.dao.trainer.TrainerDao
import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.security.SecureRandom

@Component
class TrainerRepositoryPort(
    private val trainerDao: TrainerDao,
    private val capturedDao: CapturedDao,
    ): TrainerPort {

    val encoder = BCryptPasswordEncoder(10, SecureRandom())

    override fun findAllTrainers(): List<Trainer> {
        return trainerDao.findAll().toList()
    }

    override fun findTrainerById(id: Long): Trainer? {
        val tmpTrainer = trainerDao.findById(id)
        return if (tmpTrainer.isPresent) {
            tmpTrainer.get()
        } else null
    }

    override fun findTrainerByEmail(email: String): Trainer? {
        val tmpTrainer = trainerDao.findTrainerByEmail(email)
        return if (tmpTrainer != null) {
            tmpTrainer!!
        } else null
    }

    override fun addTrainer(trainer: TrainerRequestDto): Boolean {
        return try {
            trainerDao.save(
                Trainer(
                    findAllTrainers().size.toLong() + 1,
                    trainer.name,
                    trainer.email,
                    encoder.encode(trainer.password),
                    emptyList()
                )
            )
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun removeTrainer(id: Long): Boolean {
        return try {
            val tmpTrainer = trainerDao.findById(id)
            trainerDao.delete(tmpTrainer.get())
            capturedDao.findEntriesByTrainerId(id).forEach {
                capturedDao.delete(it)
            }
            true
        } catch (ex: Exception) {
            false
        }
    }
}
