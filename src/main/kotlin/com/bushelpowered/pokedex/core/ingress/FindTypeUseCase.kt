package com.bushelpowered.pokedex.core.ingress

interface FindTypeUseCase : FindAll

fun interface FindAll{
    fun findAll() : List<String>
}



