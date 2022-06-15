package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name="pokemon_ability")
@Entity
data class PokemonAbilities(
    @Id
    val id: String,

    @Column(name="poke_id")
    val pokeId: String,


    val abilityId: String
)
