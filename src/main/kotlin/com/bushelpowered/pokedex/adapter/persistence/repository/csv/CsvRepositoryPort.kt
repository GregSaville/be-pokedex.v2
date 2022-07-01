package com.bushelpowered.pokedex.adapter.persistence.repository.csv

import com.bushelpowered.pokedex.adapter.persistence.dao.csv.*
import com.bushelpowered.pokedex.adapter.persistence.entities.csv.*
import com.bushelpowered.pokedex.core.egress.csv.CsvPort
import org.springframework.stereotype.Component

@Component
class CsvRepositoryPort(
    private val abilityDao: AbilityDao,
    private val eggGroupDao: EggGroupDao,
    private val pokemonAbilitiesDao: PokemonAbilitiesDao,
    private val pokemonEggGroupDao: PokemonEggGroupDao,
    private val pokemonModelDao: PokemonModelDao,
    private val pokemonTypesDao: PokemonTypesDao,
    private val statsDao: StatsDao
) : CsvPort {
    override fun saveModel(pokemonModelEntity: PokemonModelEntity) {
        pokemonModelDao.save(pokemonModelEntity)
    }

    override fun savePokemonTypes(pokemonType: PokemonTypes) {
        pokemonTypesDao.save(pokemonType)
    }

    override fun saveAbility(ability: Ability) {
        abilityDao.save(ability)
    }

    override fun savePokemonAbility(pokemonAbilities: PokemonAbility) {
        pokemonAbilitiesDao.save(pokemonAbilities)
    }

    override fun saveEggGroup(eggGroup: EggGroup) {
        eggGroupDao.save(eggGroup)
    }

    override fun savePokemonEggGroup(pokemonEggGroup: PokemonEggGroup) {
        pokemonEggGroupDao.save(pokemonEggGroup)
    }

    override fun saveStats(stats: Stats) {
        statsDao.save(stats)
    }

    override fun findByAbility(abilityName: String): Ability? {
        return abilityDao.findByAbility(abilityName)
    }

    override fun findByGroup(groupName: String): EggGroup? {
        return eggGroupDao.findByGroup(groupName)
    }
}