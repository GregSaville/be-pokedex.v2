package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.data.jpa.repository.JpaRepository

interface TrainerRepository : JpaRepository<Trainer, Long>{
    fun findTrainerByEmail(email: String) : Trainer?
}
