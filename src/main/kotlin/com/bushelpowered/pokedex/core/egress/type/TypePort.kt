package com.bushelpowered.pokedex.core.egress.type

import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type


interface TypePort :
    FindAll,
    GetType,
    ValidateTypes,
    Save

fun interface FindAll {
    fun findAll(): List<Type>
}

fun interface GetType {
    fun getType(type: String): Type?
}

fun interface ValidateTypes {
    fun validateTypes(types: List<String>): Boolean
}

fun interface Save{
    fun save(type: Type)
}