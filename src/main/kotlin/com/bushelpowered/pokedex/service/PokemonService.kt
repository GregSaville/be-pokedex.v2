package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.util.Optional


@Service
@Primary
class PokemonService(val db: PokemonRepository){

    val listOfTypes = listOf("bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water")
    fun findAllPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun findPokemonByName(name: String): Optional<Pokemon> = db.findByName(name)

    fun findPokemonById(id: String): Optional<Pokemon> = db.findById(id)
    fun findPokemonByIds(ids: List<String>) : MutableIterable<Pokemon> = db.findAllById(ids)

    fun findPokemonByTypes(type: String) : MutableList<Optional<Pokemon>> {
        var pokeList = db.findByType(stringToJsonFormatter(type.split(",", " ")))
        if (type.split(",", " ").size > 1) {
            pokeList = db.findByType(stringToJsonFormatter(reverseTypes(type)))
        } else {
            listOfTypes.forEach { nextType ->
                db.findByType("[\"$type\", \"$nextType\"]").forEach {
                    pokeList.add(it)
                }
                db.findByType("[\"$nextType\", \"$type\"]").forEach {
                    pokeList.add(it)
                }

            }
        }
        pokeList.sortBy { it.get().id.toInt() }
        return pokeList
    }

    private fun reverseTypes(type: String): List<String> {
        var tmpList = type.split(","," ")
        var reversedTypes = mutableListOf<String>()
        tmpList.reversed().forEach{
            reversedTypes.add(it)
        }
        return reversedTypes
    }

    private fun stringToJsonFormatter(typeList: List<String>) : String{
        var returnString = "["
        if (typeList.size > 1) {
            typeList.forEach {
                returnString = "$returnString\"$it\", "
            }
            returnString = returnString.dropLast(2)
        } else {
            returnString = "$returnString\"${typeList[0]}\""
        }
        returnString = "$returnString]"
        return returnString
    }
}