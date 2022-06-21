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
        ) name: String
    ): ResponseEntity<HashMap<String, List<Pokemon>>>{
        val tmpPokeList = pokemonService.findPokemonByName(name)
        return if (tmpPokeList.isNotEmpty()) {
            ResponseEntity.ok(tmpPokeList)
        } else ResponseEntity.notFound().build()
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["type"])
    fun getPokemonByType(@RequestParam(name = "type") type: String, @RequestParam(name="page", defaultValue = "0") page: Int): ResponseEntity<HashMap<String, List<Pokemon>>> {
        val tmpMap = pokemonService.findPokemonByType(type.split(','), page)
        return if (tmpMap["content"]!!.isNotEmpty()) {
            ResponseEntity.ok(tmpMap)
        } else ResponseEntity.notFound().build()
    }

}