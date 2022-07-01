package com.bushelpowered.pokedex.adapter.persistence.repository.type

import com.bushelpowered.pokedex.adapter.persistence.dao.type.TypeDao
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.core.egress.type.TypePort
import org.springframework.stereotype.Component

@Component
class TypeRepositoryPort(private val typeDao: TypeDao) : TypePort {

    override fun findAll(): List<Type> {
        return typeDao.findAll()
    }

    override fun validateTypes(types: List<String>): Boolean {
        types.forEach {
            if (typeDao.findByType(it) == null) {
                return false
            }
        }
        return true
    }

    override fun save(type: Type) {
        typeDao.save(type)
    }

    override fun getType(type: String): Type? {
        return typeDao.findByType(type)
    }
}