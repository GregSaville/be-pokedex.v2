package com.bushelpowered.pokedex.core.service.trainer

import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import org.springframework.stereotype.Service
import org.valiktor.ConstraintViolationException


@Service
class TrainerService(private val trainerPort: TrainerPort) : CrudTrainerUseCase {

    override fun getAllTrainers(): List<TrainerModel> {
        val listOfTrainer = mutableListOf<TrainerModel>()
        trainerPort.findAllTrainers().forEach {
            listOfTrainer.add(it.toModel())
        }
        return listOfTrainer.toList()
    }

    override fun getById(id: Long): TrainerModel? {
        val tmpTrainer = trainerPort.findTrainerById(id)
        return tmpTrainer?.toModel()
    }

    override fun addTrainer(trainer: TrainerRequestDto): Boolean {
        return try {
            if (trainerPort.findTrainerByEmail(trainer.email) == null) {
                trainerPort.addTrainer(trainer)
            } else {
                println("Trainer already exists with email : ${trainer.email}")
                false
            }
        } catch (ex: ConstraintViolationException) {
            ex.constraintViolations.map { "${it.property}: ${it.constraint.name}" }.forEach(::println)
            false
        }
    }


    override fun removeTrainer(id: Long): Boolean {
        return trainerPort.removeTrainer(id)
    }

    override fun getByEmail(email: String): TrainerModel? {
        return trainerPort.findTrainerByEmail(email)?.toModel()
    }

}

private fun Trainer.toModel(): TrainerModel {
    return TrainerModel(
        this.id,
        this.name,
        this.email,
        this.getPassword(),
        this.capturedPokemon
    )
}
