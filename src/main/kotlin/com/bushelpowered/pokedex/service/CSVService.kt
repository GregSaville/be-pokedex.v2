package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.*
import com.bushelpowered.pokedex.repositories.*
import com.bushelpowered.pokedex.utilites.CSVHelper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
class CSVService(private val helper: CSVHelper,
                 private val pokemonModelRepository: PokemonModelRepository,
                 private val typeRepository: TypeRepository,
                 private val pokemonTypesRepository: PokemonTypesRepository,
                 private val abilityRepository: AbilityRepository,
                 private val pokemonAbilitiesRepository: PokemonAbilitiesRepository,
                 private val eggGroupRepository: EggGroupRepository,
                 private val pokemonEggGroupRepository: PokemonEggGroupRepository,
                 private val statsRepository: StatsRepository
                 ) {


    fun savePokemon(pokemon: PokemonModel) = pokemonModelRepository.save(pokemon)

    fun loadCSV() {
        var typeCount = 1
        var abilityCount = 1
        var groupCount = 1
        var pokemonTypeCount = 1
        var pokemonAbilityCount = 1
        var pokemonGroupCount = 1

        val parser = helper.getCSVParser()
        parser.forEach { line ->
            val id = line[0]
            val name = line[1]
            val types = helper.dataToList(line[2])
            val height = line[3]
            val weight = line[4]
            val abilities = helper.dataToList(line[5])
            val eggGroup = helper.dataToList(line[6])
            val stats = ObjectMapper().readValue<MutableMap<Any, Any>>(line[7])
            val genus = line[8]
            val desc = line[9]
            savePokemon(PokemonModel(id, name, height, weight, genus, desc))
            statsRepository.save(Stats(id,
                stats["hp"] as Int,
                stats["speed"] as Int,
                stats["attack"] as Int,
                stats["defense"] as Int,
                stats["special-attack"] as Int,
                stats["special-defense"] as Int))

            types.forEach { type ->
                if (!isTypePresent(type)) {
                    typeRepository.save(Type(typeCount.toString(), type))
                    typeCount += 1
                }
                val foundType = typeRepository.findByType(type)
                if (foundType != null) {
                    pokemonTypesRepository.save(
                        PokemonTypes(
                            pokemonTypeCount.toString(), id, foundType.typeId
                        )
                    )
                    pokemonTypeCount += 1
                }
            }

            abilities.forEach { ability ->
                if (!isAbilityPresent(ability)) {
                    abilityRepository.save(Ability(abilityCount.toString(), ability))
                    abilityCount += 1
                }
                val foundAbility = abilityRepository.findByAbility(ability)
                if (foundAbility != null) {
                    pokemonAbilitiesRepository.save(
                        PokemonAbilities(
                            pokemonAbilityCount.toString(), id, foundAbility.abilityId
                        )
                    )
                    pokemonAbilityCount += 1
                }
            }

            eggGroup.forEach { group ->
                if (!isGroupPresent(group)) {
                    eggGroupRepository.save(EggGroup(groupCount.toString(), group))
                    groupCount += 1
                }
                val foundGroup = eggGroupRepository.findByGroup(group)
                if (foundGroup != null) {
                    pokemonEggGroupRepository.save(
                        PokemonEggGroup(
                            pokemonGroupCount.toString(), id, foundGroup.groupId
                        )
                    )
                    pokemonGroupCount += 1
                }
            }


        }
    }


    private fun isGroupPresent(group: String): Boolean {
        return eggGroupRepository.findByGroup(group) != null
    }

    private fun isAbilityPresent(ability: String): Boolean {
        return abilityRepository.findByAbility(ability) != null
    }

    private fun isTypePresent(type: String): Boolean {
        return typeRepository.findByType(type) != null
    }

}