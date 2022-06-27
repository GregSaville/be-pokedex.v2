package com.bushelpowered.pokedex.core.ingress.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerResponseDto

interface CrudTrainerUseCase :
    FindAllTrainers,
    GetTrainerById,
    AddTrainer,
    DeleteTrainer

fun interface FindAllTrainers {
    fun getAllTrainers(): List<TrainerResponseDto>
}

fun interface GetTrainerById {
    fun getById(id: Long): TrainerResponseDto?
}

fun interface AddTrainer {
    fun addTrainer(trainer: TrainerRequestDto): Boolean
}

fun interface DeleteTrainer {
    fun removeTrainer(id: Long): Boolean
}
