package com.bushelpowered.pokedex.adapter.persistence.dao.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.*
import com.bushelpowered.pokedex.adapter.persistence.repository.csv.*
import com.bushelpowered.pokedex.core.egress.csv.CsvPort
import org.springframework.stereotype.Component

@Component
class CsvDao(
    private val pokemonModelRepo: PokemonModelRepository,
    private val pokemonTypesRepo: PokemonTypesRepository,
    private val abilityRepo: AbilityRepository,
    private val pokemonAbilitiesRepo: PokemonAbilitiesRepository,
    private val eggGroupRepo: EggGroupRepository,
    private val pokemonEggGroupRepo: PokemonEggGroupRepository,
    private val statsRepo: StatsRepository
) : CsvPort {

    override fun saveModel(pokemonModel: PokemonModel) {
        pokemonModelRepo.save(pokemonModel)
    }

    override fun savePokemonTypes(pokemonType: PokemonTypes) {
        pokemonTypesRepo.save(pokemonType)
    }

    override fun saveAbility(ability: Ability) {
        abilityRepo.save(ability)
    }

    override fun savePokemonAbility(pokemonAbilities: PokemonAbility) {
        pokemonAbilitiesRepo.save(pokemonAbilities)
    }

    override fun saveEggGroup(eggGroup: EggGroup) {
        eggGroupRepo.save(eggGroup)
    }

    override fun savePokemonEggGroup(pokemonEggGroup: PokemonEggGroup) {
        pokemonEggGroupRepo.save(pokemonEggGroup)
    }

    override fun saveStats(stats: Stats) {
        statsRepo.save(stats)
    }

    override fun findByAbility(abilityName: String): Ability? {
        return abilityRepo.findByAbility(abilityName)
    }

    override fun findByGroup(groupName: String): EggGroup? {
        return eggGroupRepo.findByGroup(groupName)
    }
}