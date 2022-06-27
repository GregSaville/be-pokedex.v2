package com.bushelpowered.pokedex.adapter.persistence.entities

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name = "abilities")
@Entity
data class Ability(
    @Id
    @Column(name = "ability_id")
    val abilityId: String,

    @JsonValue
    val ability: String,

    )
