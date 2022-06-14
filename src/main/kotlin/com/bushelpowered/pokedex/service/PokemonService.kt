package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.util.Optional


@Service
@Primary
class PokemonService(private val PokemonRepository: PokemonRepository) {

    fun findAllPokemon(): List<Pokemon> = PokemonRepository.findAll()

    fun findPokemonByName(name: String): Pokemon? = PokemonRepository.findByName(name)

    fun findPokemonById(id: String): Pokemon? {
        return if (PokemonRepository.findById(id).isPresent) {
            PokemonRepository.findById(id).get()
        } else null
    }

    fun findPokemonByIds(ids: List<String>): List<Pokemon> = PokemonRepository.findAllById(ids)

    fun findPokemonByPage(page: Int): List<Pokemon> {
        val pokemonRangeMax = page * 15
        val pokemonRangeMin = ((page - 1) * 15) + 1
        val pokeIdList = mutableListOf<String>()
        for (id in pokemonRangeMin..pokemonRangeMax) {
            pokeIdList.add(id.toString())
        }
        return PokemonRepository.findAllById(pokeIdList)
    }

}

//    fun findPokemonByTypes(type: String) : List<Pokemon> {
//        var pokeList = PokemonRepository.findByType(stringsToJsonFormatter(type.split(",", "+"," ").toSet().toList())).toMutableList()
//        if (pokeList.isEmpty() && type.split(",", " ").size > 1) {
//            pokeList = PokemonRepository.findByType(stringsToJsonFormatter(type.split(",","+"," ").reversed())).toMutableList()
//        } else {
//            listOfTypes.forEach { nextType ->
//                PokemonRepository.findByType("[\"$type\", \"$nextType\"]").forEach {
//                    pokeList.add(it)
//                }
//                PokemonRepository.findByType("[\"$nextType\", \"$type\"]").forEach {
//                    pokeList.add(it)
//                }
//            }
//        }
//        pokeList.sortBy { it.id.toInt() }
//        return pokeList
//    }

//    private fun stringsToJsonFormatter(typeList: List<String>) : String{
//        var returnString = "["
//        if (typeList.size > 1) {
//            typeList.forEach {
//                if(listOfTypes.contains(it)) {
//                    returnString = "$returnString\"$it\", "
//                }
//            }
//                returnString = returnString.dropLast(2)
//        } else if (typeList.isNotEmpty()){
//            returnString = "$returnString\"${typeList[0]}\""
//        }
//        returnString = "$returnString]"
//        return returnString
//    }
//}