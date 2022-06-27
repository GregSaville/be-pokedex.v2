package com.bushelpowered.pokedex.core.service.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerResponseDto
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import org.springframework.stereotype.Service
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotNull
import org.valiktor.validate

@Service
class TrainerService(private val trainerPort: TrainerPort) : CrudTrainerUseCase {

    override fun getAllTrainers(): List<TrainerResponseDto> {
        val listOfTrainerResponse = mutableListOf<TrainerResponseDto>()
        trainerPort.findAllTrainers().forEach {
            listOfTrainerResponse.add(TrainerResponseDto(it.id, it.name, it.email))
        }
        return listOfTrainerResponse.toList()
    }

    override fun getById(id: Long): TrainerResponseDto? {
        val tmpTrainer = trainerPort.findTrainerById(id)
        return if (tmpTrainer != null) {
            TrainerResponseDto(tmpTrainer.id, tmpTrainer.name, tmpTrainer.email)
        } else null
    }

    override fun addTrainer(trainer: TrainerRequestDto): Boolean {
        return try {
            validate(TrainerRequestDto(trainer.name, trainer.email, trainer.password)) {
                validate(TrainerRequestDto::name).isNotNull().hasSize(min = 2)
                validate(TrainerRequestDto::email).isEmail()
                validate(TrainerRequestDto::password).hasSize(min = 6)
            }
            trainerPort.addTrainer(trainer)
        } catch (ex: ConstraintViolationException) {
            ex.constraintViolations.map { "${it.property}: ${it.constraint.name}" }.forEach(::println)
            false
        }
    }


    override fun removeTrainer(id: Long): Boolean {
        return trainerPort.removeTrainer(id)
    }

}