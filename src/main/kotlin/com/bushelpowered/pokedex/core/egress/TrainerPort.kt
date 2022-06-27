package com.bushelpowered.pokedex.core.egress

import com.bushelpowered.pokedex.adapter.persistence.entities.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.TrainerRequestDTO

interface TrainerPort:
        FindAllTrainers,
        FindTrainerById,
        FindTrainerByEmail,
        AddTrainer,
        RemoveTrainer

fun interface FindAllTrainers{
    fun findAllTrainers() : List<Trainer>
}

fun interface FindTrainerById{
    fun findTrainerById(id: Long) : Trainer?
}

fun interface FindTrainerByEmail{
    fun findTrainerByEmail(email : String) : Trainer?
}

fun interface AddTrainer{
    fun addTrainer(trainer: TrainerRequestDTO) : Boolean
}

fun interface RemoveTrainer{
    fun removeTrainer(id: Long) : Boolean
}