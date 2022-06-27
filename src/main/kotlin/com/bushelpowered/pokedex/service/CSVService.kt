package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.adapter.persistence.entities.*
import com.bushelpowered.pokedex.adapter.persistence.entities.Type
import com.bushelpowered.pokedex.adapter.persistence.repository.*
import com.bushelpowered.pokedex.core.utilites.CSVHelper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
class CSVService(
    private val helper: CSVHelper,
    private val pokemonModelRepository: PokemonModelRepository,
    private val typeRepositoryPort: TypeRepositoryPort,
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
            statsRepository.save(
                Stats(
                    id,
                    stats["hp"] as Int,
                    stats["speed"] as Int,
                    stats["attack"] as Int,
                    stats["defense"] as Int,
                    stats["special-attack"] as Int,
                    stats["special-defense"] as Int
                )
            )
            savePokemon(handleModelSave(id,name,height,weight,genus,desc))
            types.forEach { type ->
                if (!isTypePresent(type)) {
                    typeRepositoryPort.save(Type(typeCount.toString(), type))
                    typeCount += 1
                }
                val foundType = typeRepositoryPort.findByType(type)
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

    private fun handleModelSave(id: String, name: String, height: String, weight: String, genus: String, desc: String) : PokemonModel {
        when(name){
            "Mr. Mime" -> {
                return PokemonModel(
                    id,
                    "Mr.Mime",
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/mr-mime.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/mr-mime.png",
                    "https://projectpokemon.org/images/normal-sprite/mr.mime.gif",
                    "https://projectpokemon.org/images/shiny-sprite/mr._mime.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/mr-mime.png")
            }
            "Nidoran♂" -> {
                return PokemonModel(
                    id,
                    name,
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/nidoran-m.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/nidoran-m.png",
                    "https://projectpokemon.org/images/normal-sprite/nidoran_m.gif",
                    "https://projectpokemon.org/images/shiny-sprite/nidoran_m.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/nidoran-m.png")
            }
            "Nidoran♀" -> {
                return PokemonModel(
                    id,
                    name,
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/nidoran-f.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/nidoran-f.png",
                    "https://projectpokemon.org/images/normal-sprite/nidoran_f.gif",
                    "https://projectpokemon.org/images/shiny-sprite/nidoran_f.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/nidoran-f.png")
            }
            "Farfetch’d" -> {
                return PokemonModel(
                    id,
                    name,
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/farfetchd.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/farfetchd.png",
                    "https://projectpokemon.org/images/normal-sprite/farfetchd.gif",
                    "https://projectpokemon.org/images/shiny-sprite/farfetchd.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/farfetchd.png")
            }
            "Mime Jr." -> {
                return PokemonModel(
                    id,
                    name,
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/mime-jr.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/mime-jr.png",
                    "https://projectpokemon.org/images/normal-sprite/mime_jr.gif",
                    "https://projectpokemon.org/images/shiny-sprite/mime_jr.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/mime-jr.png")
            }
            else -> {
                return PokemonModel(
                    id,
                    name,
                    height,
                    weight,
                    genus,
                    desc,
                    "https://img.pokemondb.net/sprites/home/normal/1x/${name.lowercase()}.png",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/regular/${name.lowercase()}.png",
                    "https://projectpokemon.org/images/normal-sprite/${name.lowercase()}.gif",
                    "https://projectpokemon.org/images/shiny-sprite/${name.lowercase()}.gif",
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/${name.lowercase()}.png"
                )
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
        return typeRepositoryPort.findByType(type) != null
    }

}