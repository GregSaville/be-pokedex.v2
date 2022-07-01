package com.bushelpowered.pokedex.adapter.persistence.dao.trainer


import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TrainerDao : JpaRepository<Trainer, Long> {
    fun findTrainerByEmail(email: String): Trainer?
}