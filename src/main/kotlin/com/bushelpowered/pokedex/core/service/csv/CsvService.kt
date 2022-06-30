package com.bushelpowered.pokedex.core.service.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.*
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.adapter.persistence.repository.type.TypeRepositoryPort
import com.bushelpowered.pokedex.core.egress.csv.CsvPort
import com.bushelpowered.pokedex.core.ingress.csv.CsvUseCase
import com.bushelpowered.pokedex.core.service.csv.utility.CSVHelper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
class CsvService(
    private val helper: CSVHelper,
    private val csvPort: CsvPort,
    private val typeRepositoryPort: TypeRepositoryPort,
) : CsvUseCase {

    override fun loadCsv() {
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
            csvPort.saveStats(
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
            savePokemon(handleModelSave(id, name, height, weight, genus, desc))
            types.forEach { type ->
                if (!isTypePresent(type)) {
                    typeRepositoryPort.save(Type(typeCount.toString(), type))
                    typeCount += 1
                }
                val foundType = typeRepositoryPort.getType(type)
                if (foundType != null) {
                    csvPort.savePokemonTypes(
                        PokemonTypes(
                            pokemonTypeCount.toString(), id, foundType.typeId
                        )
                    )
                    pokemonTypeCount += 1
                }
            }

            abilities.forEach { ability ->
                if (!isAbilityPresent(ability)) {
                    csvPort.saveAbility(Ability(abilityCount.toString(), ability))
                    abilityCount += 1
                }
                val foundAbility = csvPort.findByAbility(ability)
                if (foundAbility != null) {
                    csvPort.savePokemonAbility(
                        PokemonAbility(
                            pokemonAbilityCount.toString(), id, foundAbility.abilityId
                        )
                    )
                    pokemonAbilityCount += 1
                }
            }

            eggGroup.forEach { group ->
                if (!isGroupPresent(group)) {
                    csvPort.saveEggGroup(EggGroup(groupCount.toString(), group))
                    groupCount += 1
                }
                val foundGroup = csvPort.findByGroup(group)
                if (foundGroup != null) {
                    csvPort.savePokemonEggGroup(
                        PokemonEggGroup(
                            pokemonGroupCount.toString(), id, foundGroup.groupId
                        )
                    )
                    pokemonGroupCount += 1
                }
            }
        }
    }

    private fun savePokemon(pokemon: PokemonModelEntity) = csvPort.saveModel(pokemon)

    private fun handleModelSave(
        id: String,
        name: String,
        height: String,
        weight: String,
        genus: String,
        desc: String
    ): PokemonModelEntity {
        when (name) {
            "Mr. Mime" -> {
                return PokemonModelEntity(
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
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/mr-mime.png"
                )
            }
            "Nidoran♂" -> {
                return PokemonModelEntity(
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
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/nidoran-m.png"
                )
            }
            "Nidoran♀" -> {
                return PokemonModelEntity(
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
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/nidoran-f.png"
                )
            }
            "Farfetch’d" -> {
                return PokemonModelEntity(
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
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/farfetchd.png"
                )
            }
            "Mime Jr." -> {
                return PokemonModelEntity(
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
                    "https://raw.githubusercontent.com/msikma/pokesprite/master/pokemon-gen8/shiny/mime-jr.png"
                )
            }
            else -> {
                return PokemonModelEntity(
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
        return csvPort.findByGroup(group) != null
    }

    private fun isAbilityPresent(ability: String): Boolean {
        return csvPort.findByAbility(ability) != null
    }

    private fun isTypePresent(type: String): Boolean {
        return typeRepositoryPort.getType(type) != null
    }

}