package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getPokemonByPage(@RequestParam(name = "page", required = false, defaultValue = "0") page : Int) :ResponseEntity<Page<Pokemon>>{
        return if(page > -1 && page < 37) {
                ResponseEntity.ok(pokemonService.findPokemonByPage(page))
            } else(ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon>{
        return if(pokemonService.findPokemonById(pokeId) != null) {
                ResponseEntity.ok(pokemonService.findPokemonById(pokeId))
            } else(ResponseEntity.notFound().build())
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["name"])
    fun getPokemonByName(@RequestParam(name = "name") name :String) : ResponseEntity<List<Pokemon>>{
        val tmpPokeList = pokemonService.findPokemonByName(name)
        return if(tmpPokeList.isNotEmpty()){
            ResponseEntity.ok(tmpPokeList)
        }else ResponseEntity.notFound().build()
    }

}