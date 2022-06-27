package com.bushelpowered.pokedex.core.service.pokemon

import com.bushelpowered.pokedex.core.domain.Pokemon
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

    override fun findByPage(page: Int): Page<Pokemon> {
        return pokemonPort.findAll(PageRequest.of(page, 15))
    }

    override fun findById(id: String): Pokemon? {
        return pokemonPort.findById(id)
    }

    override fun findByName(name: String, page: Int): Page<Pokemon> {
        return pokemonPort.findByNameContaining(name, PageRequest.of(page, 15))
    }

    override fun findByType(type: List<String>, page: Int): Page<Pokemon>? {
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(listOf(type[0].dropLast(1)))) {
            val result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(type[0].dropLast(1))))
            result.forEach {
                if (it.type.size == 1) {
                    pokeList.add(it.id)
                }
            }
            pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }

    override fun findByTypes(types: List<String>, page: Int): Page<Pokemon>? {
        val typeOneList = mutableListOf<String>()
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(types)) {
            var result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(types[0])))
            result.forEach {
                typeOneList.add(it.id)
            }
            result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(types[1])))
            result.forEach {
                if (typeOneList.contains(it.id)) {
                    pokeList.add(it.id)
                }
            }
            pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }

    override fun findWithType(type: List<String>, page: Int): Page<Pokemon>? {
        val pokeList = mutableListOf<String>()
        return if (typePort.validateTypes(type)) {
            val result = pokemonPort.findPokemonByTypeIn(listOf(typePort.getType(type[0])))
            result.forEach {
                pokeList.add(it.id)
            }
            pokemonPort.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }
}