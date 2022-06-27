package com.bushelpowered.pokedex.adapter.persistence.dao

import com.bushelpowered.pokedex.adapter.persistence.repository.TypeRepositoryPort
import com.bushelpowered.pokedex.adapter.persistence.entities.Type
import com.bushelpowered.pokedex.core.egress.TypePort
import org.springframework.stereotype.Component


@Component
class TypeDao(private val repo: TypeRepositoryPort) : TypePort {

    override fun findAll(): List<Type> {
        return repo.findAll()
    }

    override fun validateTypes(types: List<String>): Boolean {
        types.forEach {
            if (repo.findByType(it) == null) {
                return false
            }
        }
        return true
    }

    override fun getType(type: String): Type {
        return repo.findByType(type)!!
    }

}