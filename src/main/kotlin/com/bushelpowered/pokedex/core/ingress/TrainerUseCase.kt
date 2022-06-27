package com.bushelpowered.pokedex.core.ingress

import com.bushelpowered.pokedex.adapter.web.dto.TrainerRequestDTO
import com.bushelpowered.pokedex.adapter.web.dto.TrainerResponseDTO

interface CrudTrainerUseCase:
        FindAllTrainers,
        GetTrainerById,
        AddTrainer,
        DeleteTrainer

fun interface FindAllTrainers{
    fun getAllTrainers() : List<TrainerResponseDTO>
}

fun interface GetTrainerById{
    fun getById(id: Long) : TrainerResponseDTO?
}

fun interface AddTrainer{
    fun addTrainer(trainer: TrainerRequestDTO) : Boolean
}

fun interface DeleteTrainer{
    fun removeTrainer(id : Long) : Boolean
}
