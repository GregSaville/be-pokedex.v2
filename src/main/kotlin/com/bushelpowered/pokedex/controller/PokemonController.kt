package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.service.dto.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(val service: PokemonService) {

    @GetMapping("/pokemon")
    fun index(): MutableIterable<Pokemon> = service.findAllPokemon()

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon>{
        return service.findPokemonById(pokeId).map { pokemon ->
            ResponseEntity.ok(pokemon)
        }.orElse(ResponseEntity.notFound().build())
    }

}