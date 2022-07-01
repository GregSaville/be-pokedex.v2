package com.bushelpowered.pokedex.adapter.web.controller.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import com.bushelpowered.pokedex.core.domain.model.pokemon.PokemonModel
import com.bushelpowered.pokedex.core.ingress.pokemon.FindPokemonUseCase
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(private val findPokemonUseCase: FindPokemonUseCase) {

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getPokemonByPage(
        @RequestParam(
            name = "page",
            required = false,
            defaultValue = "0"
        ) page: Int
    ): ResponseEntity<Map<String, Any>> {
        return if (page > -1 && page < 37) {
            ResponseEntity.ok(findPokemonUseCase.findByPage(page))
        } else (ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<PokemonModel> {
        val result = findPokemonUseCase.findById(pokeId)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else (ResponseEntity.notFound().build())
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["name"])
    fun getPokemonByName(
        @RequestParam(
            name = "name",
            defaultValue = "pikachu"
        ) name: String,
        @RequestParam(name = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any>> {
        val tmpPokeList = findPokemonUseCase.findByName(name, page)
        return ResponseEntity.ok(tmpPokeList)
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["type"])
    fun getPokemonByType(
        @RequestParam(name = "type") type: String,
        @RequestParam(name = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any>> {
        val input = type.split(',')
        val tmpResult = if (input.size == 1) {
            if (input[0].endsWith("~")) {
                findPokemonUseCase.findByType(input, page)
            } else findPokemonUseCase.findWithType(input, page)
        } else {
            findPokemonUseCase.findByTypes(input, page)
        }
        return if (tmpResult != null) {
            ResponseEntity.ok(tmpResult)
        } else ResponseEntity.notFound().build()
    }

}