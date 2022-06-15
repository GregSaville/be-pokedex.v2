package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(private val service: PokemonService) {

    @GetMapping("/all")
    fun getAll(): ResponseEntity<List<Pokemon>> {
        return if(service.findAllPokemon() != null) {
            ResponseEntity.ok(service.findAllPokemon())
        } else(ResponseEntity.notFound().build())
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getPokemonByPage(@RequestParam(name = "page", required = false, defaultValue = "1") page : Int) :ResponseEntity<List<Pokemon>>{
    return if(service.findPokemonByPage(page) != null) {
        ResponseEntity.ok(service.findPokemonByPage(page))
    } else(ResponseEntity.notFound().build())
}

//    @RequestMapping(value= [""],method = [RequestMethod.GET], params = ["name"])
//    fun getPokemonByName(@RequestParam(name = "name", required = false) name : String) :ResponseEntity<Pokemon> {
//        return  if(service.findPokemonByName(name) != null) {
//            ResponseEntity.ok(service.findPokemonByName(name))
//        } else(ResponseEntity.notFound().build())
//    }

//    @RequestMapping(value = [""], method = [RequestMethod.GET], params = ["type"])
//    fun getPokemonByTypes(@RequestParam type : String) : ResponseEntity<List<Pokemon>> {
//        return  if(service.findPokemonByTypes(type) != null) {
//            ResponseEntity.ok(service.findPokemonByTypes(type))
//        } else(ResponseEntity.notFound().build())
//        }

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable(value = "id") pokeId: String): ResponseEntity<Pokemon>{
        return if(service.findPokemonById(pokeId) != null) {
                ResponseEntity.ok(service.findPokemonById(pokeId))
            } else(ResponseEntity.notFound().build())
    }
}