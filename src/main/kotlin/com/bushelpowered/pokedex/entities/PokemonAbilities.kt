package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name = "pokemon_ability")
@Entity
data class PokemonAbilities(
    @Id
    @Column(name = "pokemon_ability_id")
    val id: String,

    @Column(name = "poke_id")
    val pokeId: String,

    @Column(name = "ability_id")
    val abilityId: String
)
