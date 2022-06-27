package com.bushelpowered.pokedex.adapter.persistence.entities.csv

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "stats")
@Entity
data class Stats(
    @JsonIgnore
    @Column(name = "poke_id")
    @Id
    val id: String,

    val hp: Int,

    val speed: Int,

    val attack: Int,

    val defense: Int,

    @Column(name = "specialattack")
    val specialAttack: Int,

    @Column(name = "specialdefense")
    val specialDefense: Int
)
