package com.bushelpowered.pokedex.core.ingress.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel

interface CrudTrainerUseCase :
        FindAllTrainers,
        GetTrainerById,
        AddTrainer,
        DeleteTrainer,
        GetTrainerByEmail

fun interface FindAllTrainers {
    fun getAllTrainers(): List<TrainerModel>
}

fun interface GetTrainerByEmail {
    fun getByEmail(email: String): TrainerModel?
}

fun interface GetTrainerById {
    fun getById(id: Long): TrainerModel?
}

fun interface AddTrainer {
    fun addTrainer(trainer: TrainerRequestDto): Boolean
}

fun interface DeleteTrainer {
    fun removeTrainer(id: Long): Boolean
}
