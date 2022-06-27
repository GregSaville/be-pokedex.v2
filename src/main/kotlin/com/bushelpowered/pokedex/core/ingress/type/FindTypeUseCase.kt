package com.bushelpowered.pokedex.core.ingress.type

interface FindTypeUseCase : FindAll

fun interface FindAll {
    fun findAll(): List<String>
}



