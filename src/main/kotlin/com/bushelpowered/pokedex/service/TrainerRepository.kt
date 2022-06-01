package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.data.jpa.repository.JpaRepository

interface TrainerRepository : JpaRepository<Trainer, Long>
