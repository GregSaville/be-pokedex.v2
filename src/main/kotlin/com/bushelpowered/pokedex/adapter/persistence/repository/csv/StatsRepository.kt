package com.bushelpowered.pokedex.adapter.persistence.repository.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.Stats
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatsRepository : JpaRepository<Stats, String>