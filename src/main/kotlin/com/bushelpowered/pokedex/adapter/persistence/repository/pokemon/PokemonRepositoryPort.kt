package com.bushelpowered.pokedex.adapter.persistence.repository.pokemon

import com.bushelpowered.pokedex.adapter.persistence.dao.pokemon.PokemonDao
import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.core.egress.pokemon.PokemonPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class PokemonRepositoryPort(private val pokemonDao: PokemonDao) : PokemonPort{


    override fun findAll(pageRequest: PageRequest): Page<Pokemon> {
        return pokemonDao.findAll(pageRequest)
    }

    override fun findByIdIn(pokeIdList: List<String>, page: PageRequest): Page<Pokemon> {
        return pokemonDao.findByIdIn(pokeIdList, page)
    }

    override fun findByNameContaining(name: String, page: PageRequest): Page<Pokemon> {
        return pokemonDao.findByNameContaining(name, page)
    }

    override fun findPokemonByTypeIn(type: List<Type>): List<Pokemon> {
        return pokemonDao.findPokemonByTypeIn(type)
    }

    override fun findById(id: String): Pokemon? {
        val result = pokemonDao.findById(id)
        return if (result.isPresent) {
            result.get()
        } else null
    }

}

