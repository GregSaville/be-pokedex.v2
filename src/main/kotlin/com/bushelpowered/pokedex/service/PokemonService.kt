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

    fun findPokemonByPage(page: Int): MutableIterable<Pokemon> {
        val pokemonRangeMax = page * 15
        val pokemonRangeMin = ((page-1) * 15 )+ 1
        var pokeIdList = mutableListOf<String>()
        for (id in pokemonRangeMin..pokemonRangeMax){
            pokeIdList.add(id.toString())
        }
        return db.findAllById(pokeIdList)
    }

    fun findPokemonByTypes(type: String) : MutableList<Optional<Pokemon>> {
        var pokeList = db.findByType(stringsToJsonFormatter(type.split(",", "+"," ").toSet().toList()))
        if (pokeList.isEmpty() && type.split(",", " ").size > 1) {
            pokeList = db.findByType(stringsToJsonFormatter(type.split(",","+"," ").reversed()))
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

    private fun stringsToJsonFormatter(typeList: List<String>) : String{
        var returnString = "["
        if (typeList.size > 1) {
            typeList.forEach {
                if(listOfTypes.contains(it)) {
                    returnString = "$returnString\"$it\", "
                }
            }
                returnString = returnString.dropLast(2)
        } else if (typeList.isNotEmpty()){
            returnString = "$returnString\"${typeList[0]}\""
        }
        returnString = "$returnString]"
        return returnString
    }
}