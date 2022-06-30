package com.bushelpowered.pokedex.core.egress.trainer

import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto

interface TrainerPort :
    FindAllTrainers,
    FindTrainerById,
    FindTrainerByEmail,
    AddTrainer,
    RemoveTrainer

fun interface FindAllTrainers {
    fun findAllTrainers(): List<Trainer>
}

fun interface FindTrainerById {
    fun findTrainerById(id: Long): Trainer?
}

fun interface FindTrainerByEmail {
    fun findTrainerByEmail(email: String): Trainer?
}

fun interface AddTrainer {
    fun addTrainer(trainer: TrainerRequestDto): Boolean
}

fun interface RemoveTrainer {
    fun removeTrainer(id: Long): Boolean
}
