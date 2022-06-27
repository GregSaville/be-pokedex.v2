package com.bushelpowered.pokedex.core.egress

import com.bushelpowered.pokedex.adapter.persistence.entities.Type


interface TypePort :
        FindAll,
        GetType,
        ValidateTypes

fun interface FindAll{
    fun findAll() : List<Type>
}

fun interface GetType{
    fun getType(type: String) : Type
}

fun interface ValidateTypes{
    fun validateTypes(types: List<String>) : Boolean
}