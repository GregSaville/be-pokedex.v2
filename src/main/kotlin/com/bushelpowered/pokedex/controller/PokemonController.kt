package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(val service: PokemonService) {

    @GetMapping("")
    fun index(): MutableIterable<Pokemon> = service.findAllPokemon()

    @RequestMapping(value= ["/"],method = [RequestMethod.GET], params = ["name"])
    fun getPokemonByName(@RequestParam name : String) = service.findPokemonByName(name)

    @RequestMapping(value = ["/"], method = [RequestMethod.GET], params = ["type"])
    fun getPokemonByTypes(@RequestParam type : String) = service.findPokemonByTypes(type)

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon>{
        return service.findPokemonById(pokeId).map { pokemon ->
            println(pokemon.type)
            ResponseEntity.ok(pokemon)
        }.orElse(ResponseEntity.notFound().build())
    }
}