package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.utilites.ValidateHelper
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getPokemonByPage(
        @RequestParam(
            name = "page",
            required = false,
            defaultValue = "0"
        ) page: Int
    ): ResponseEntity<Page<Pokemon>> {
        return if (page > -1 && page < 37) {
            ResponseEntity.ok(pokemonService.findPokemonByPage(page))
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon> {
        return if (pokemonService.findPokemonById(pokeId) != null) {
            ResponseEntity.ok(pokemonService.findPokemonById(pokeId))
        } else (ResponseEntity.notFound().build())
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["name"])
    fun getPokemonByName(
        @RequestParam(
            name = "name",
            defaultValue = "pikachu"
        ) name: String,
        @RequestParam(name = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Page<Pokemon>> {
        val tmpPokeList = pokemonService.findPokemonByName(name, page)
        return ResponseEntity.ok(tmpPokeList)
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["type"])
    fun getPokemonByType(
        @RequestParam(name = "type") type: String,
        @RequestParam(name = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Page<Pokemon>> {
        val input = type.split(',')
        val tmpResult = if (input.size == 1) {
            if (input[0].endsWith("~")) {
                pokemonService.findPokemonByType(input, page)
            } else pokemonService.findPokemonWithType(input, page)
        } else {
            pokemonService.findPokemonByTypes(input, page)
        }
        return if (tmpResult != null) {
            ResponseEntity.ok(tmpResult)
        } else ResponseEntity.notFound().build()
    }

}