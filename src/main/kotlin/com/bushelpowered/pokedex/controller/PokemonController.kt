package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(val service: PokemonService) {

    @GetMapping("/all")
    fun index(): MutableIterable<Pokemon> = service.findAllPokemon()

    @GetMapping("")
    fun defaultPage() : MutableIterable<Pokemon> = getPokemonByPage(1)

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["page"])
    fun getPokemonByPage(@RequestParam page : Int) = service.findPokemonByPage(page)

    @RequestMapping(value= ["/"],method = [RequestMethod.GET], params = ["name"])
    fun getPokemonByName(@RequestParam name : String) = service.findPokemonByName(name)

    @RequestMapping(value = ["/"], method = [RequestMethod.GET], params = ["type"])
    fun getPokemonByTypes(@RequestParam type : String) = service.findPokemonByTypes(type)

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon>{
        return service.findPokemonById(pokeId).map { pokemon ->
            ResponseEntity.ok(pokemon)
        }.orElse(ResponseEntity.notFound().build())
    }
}