package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*


@Table(name="abilities")
@Entity
data class Ability(
    @Id
    @JoinTable(name="pokemon_ability", joinColumns = [JoinColumn(name = "ability_id")], inverseJoinColumns = [JoinColumn(name="ability_id")])
    val abilityId: String,

    @JsonValue
    val ability: String,

)
