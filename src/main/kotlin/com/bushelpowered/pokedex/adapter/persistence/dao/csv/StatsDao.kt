package com.bushelpowered.pokedex.adapter.persistence.dao.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.Stats
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatsDao : JpaRepository<Stats, String>