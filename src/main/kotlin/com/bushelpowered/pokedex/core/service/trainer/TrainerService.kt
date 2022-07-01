package com.bushelpowered.pokedex.core.service.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerResponseDto
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel
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

    override fun getAllTrainers(): List<TrainerModel> {
        val listOfTrainer = mutableListOf<TrainerModel>()
        trainerPort.findAllTrainers().forEach {
            listOfTrainer.add(TrainerModel(it.id, it.name, it.email, it.getPassword(), it.capturedPokemon))
        }
        return listOfTrainer.toList()
    }

    override fun getById(id: Long): TrainerModel? {
        val tmpTrainer = trainerPort.findTrainerById(id)
        return if (tmpTrainer != null) {
            TrainerModel(tmpTrainer.id, tmpTrainer.name, tmpTrainer.email, tmpTrainer.getPassword(), tmpTrainer.capturedPokemon)
        } else null
    }

    override fun addTrainer(trainer: TrainerRequestDto): Boolean {
        return try {
            if(trainerPort.findTrainerByEmail(trainer.email) == null){
                trainerPort.addTrainer(trainer)
            }else{
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

}