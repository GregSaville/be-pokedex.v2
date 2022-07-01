package com.bushelpowered.pokedex.core.service.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import com.bushelpowered.pokedex.core.domain.model.pokemon.PokemonModel
import com.bushelpowered.pokedex.core.egress.pokemon.PokemonPort
import com.bushelpowered.pokedex.core.egress.type.TypePort
import com.bushelpowered.pokedex.core.ingress.pokemon.FindPokemonUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PokemonService(
    private val pokemonPort: PokemonPort,
    private val typePort: TypePort
) : FindPokemonUseCase {

    override fun findByPage(page: Int): Map<String, Any> {
      return dataToMap(pokemonPort.findAll(PageRequest.of(page, 15)))
    }

    override fun findById(id: String): PokemonModel? {
        val result = pokemonPort.findById(id)
       result?.let {
            return it.toModel()
        } ?: return null
    }

    override fun findByName(name: String, page: Int): Map<String, Any> {
        return dataToMap(pokemonPort.findByNameContaining(name, PageRequest.of(page, 15)))
    }

    override fun findByType(type: List<String>, page: Int): Map<String, Any>? {
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(listOf(type[0].dropLast(1)))) {
            val result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(type[0].dropLast(1))!!))
            result.forEach {
                if (it.type.size == 1) {
                    pokeList.add(it.id)
                }
            }
            dataToMap(pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15)))
        } else null
    }

    override fun findByTypes(types: List<String>, page: Int): Map<String, Any>? {
        val typeOneList = mutableListOf<String>()
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(types)) {
            var result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(types[0])!!))
            result.forEach {
                typeOneList.add(it.id)
            }
            result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(types[1])!!))
            result.forEach {
                if (typeOneList.contains(it.id)) {
                    pokeList.add(it.id)
                }
            }
            dataToMap(pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15)))
        } else null
    }

    override fun findWithType(type: List<String>, page: Int): Map<String, Any>? {
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(type)) {
            val result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(type[0])!!))
            result.forEach {
                pokeList.add(it.id)
            }
            dataToMap(pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15)))
        } else null
    }

    private fun dataToMap(data : Page<Pokemon>): Map<String, Any> {
        val returnPage = mutableMapOf<String, Any>()
        val pokemonList = mutableListOf<PokemonModel>()
        data.forEach{
            pokemonList.add(it.toModel())
        }
        returnPage["content"] = pokemonList.toList()
        returnPage["totalPages"] = data.totalPages
        returnPage["currentPage"] = data.number
        return returnPage.toMap()
    }

    private fun Pokemon.toModel() : PokemonModel{
        return PokemonModel(
            this.id,
            this.name,
            this.type,
            this.height,
            this.weight,
            this.abilities,
            this.eggGroups,
            this.stats,
            this.genus,
            this.description,
            this.image,
            this.sprite,
            this.gif,
            this.shinyGif,
            this.shinySprite)
    }

}