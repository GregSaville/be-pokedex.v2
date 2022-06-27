package com.bushelpowered.pokedex.adapter.persistence.dao

import com.bushelpowered.pokedex.adapter.persistence.repository.PokemonRepositoryPort
import com.bushelpowered.pokedex.adapter.persistence.entities.Type
import com.bushelpowered.pokedex.core.domain.Pokemon
import com.bushelpowered.pokedex.core.egress.PokemonPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component


@Component
class PokemonDao(private val repo: PokemonRepositoryPort) : PokemonPort {

    override fun findAll(pageRequest: PageRequest): Page<Pokemon> {
        return repo.findAll(pageRequest)
    }

    override fun findByIdIn(pokeIdList: List<String>, page: PageRequest): Page<Pokemon> {
        return repo.findByIdIn(pokeIdList, page)
    }

    override fun findByNameContaining(name: String, page: PageRequest): Page<Pokemon> {
        return repo.findByNameContaining(name, page)
    }

    override fun findPokemonByTypeIn(type: List<Type>): List<Pokemon> {
        return repo.findPokemonByTypeIn(type)
    }

    override fun findById(id: String): Pokemon? {
        val result = repo.findById(id)
        return if(result.isPresent){
            result.get()
        }else null
    }

}