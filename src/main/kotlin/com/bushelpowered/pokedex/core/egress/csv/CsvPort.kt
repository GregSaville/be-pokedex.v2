package com.bushelpowered.pokedex.core.egress.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.*

interface CsvPort :
    SavePokemonModel,
    SavePokemonTypes,
    SaveAbility,
    SavePokemonAbility,
    SaveEggGroup,
    SavePokemonEggGroup,
    SaveStats,
    FindByAbility,
    FindByGroup

fun interface SavePokemonModel {
    fun saveModel(pokemonModel: PokemonModel)
}

fun interface SavePokemonTypes {
    fun savePokemonTypes(pokemonType: PokemonTypes)
}

fun interface SaveAbility {
    fun saveAbility(ability: Ability)
}

fun interface SavePokemonAbility {
    fun savePokemonAbility(pokemonAbilities: PokemonAbility)
}

fun interface SaveEggGroup {
    fun saveEggGroup(eggGroup: EggGroup)
}

fun interface SavePokemonEggGroup {
    fun savePokemonEggGroup(pokemonEggGroup: PokemonEggGroup)
}

fun interface SaveStats {
    fun saveStats(stats: Stats)
}

fun interface FindByAbility {
    fun findByAbility(abilityName: String): Ability?
}

fun interface FindByGroup {
    fun findByGroup(groupName: String): EggGroup?
}