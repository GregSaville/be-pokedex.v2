package com.bushelpowered.pokedex.core.service

import com.bushelpowered.pokedex.adapter.web.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.adapter.web.dto.TrainerResponseDTO
import com.bushelpowered.pokedex.core.egress.TrainerPort
import com.bushelpowered.pokedex.core.ingress.CrudTrainerUseCase
import org.springframework.stereotype.Service
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotNull
import org.valiktor.validate

@Service
class TrainerService(private val trainerPort: TrainerPort) : CrudTrainerUseCase {

    override fun getAllTrainers(): List<TrainerResponseDTO> {
        val listOfTrainerResponse = mutableListOf<TrainerResponseDTO>()
        trainerPort.findAllTrainers().forEach{
            listOfTrainerResponse.add(TrainerResponseDTO(it.id,it.name,it.email))
        }
        return listOfTrainerResponse.toList()
    }

    override fun getById(id: Long): TrainerResponseDTO? {
        val tmpTrainer = trainerPort.findTrainerById(id)
        return if(tmpTrainer != null){
            TrainerResponseDTO(tmpTrainer.id, tmpTrainer.name, tmpTrainer.email)
        } else null
    }

    override fun addTrainer(trainer: TrainerRequestDTO): Boolean {
        return try {
                validate(TrainerRequestDTO(trainer.name, trainer.email, trainer.password)) {
                    validate(TrainerRequestDTO::name).isNotNull().hasSize(min = 2)
                    validate(TrainerRequestDTO::email).isEmail()
                    validate(TrainerRequestDTO::password).hasSize(min = 6)
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