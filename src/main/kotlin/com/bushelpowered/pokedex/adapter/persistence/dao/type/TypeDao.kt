package com.bushelpowered.pokedex.adapter.persistence.dao.type

import com.bushelpowered.pokedex.adapter.persistence.repository.type.TypeRepositoryPort
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.core.egress.type.TypePort
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
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