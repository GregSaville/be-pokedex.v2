package com.bushelpowered.pokedex.adapter.persistence.entities.capture

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "captured")
@Entity
data class Captured(
    @Id
    @Column(name = "captured_id")
    private val id: String,

    @Column(name = "trainer_id")
    val trainerId: Long,

    @Column(name = "poke_id")
    val pokeId: String
)
