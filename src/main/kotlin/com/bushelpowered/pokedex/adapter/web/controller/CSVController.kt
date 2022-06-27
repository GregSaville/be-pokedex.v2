package com.bushelpowered.pokedex.adapter.web.controller

import com.bushelpowered.pokedex.core.service.PokemonService
import com.bushelpowered.pokedex.service.CSVService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/admin")
class CSVController(
    private val CSVService: CSVService,
    private val pokemonService: PokemonService
) {

    @PutMapping("/loadCSV")
    fun loadCSV(): ResponseEntity<String> {
        return if (pokemonService.findById("1") == null) {
            CSVService.loadCSV()
            ResponseEntity.ok("Loaded CSV")
        } else ResponseEntity.badRequest().body("CSV already Loaded")
    }

}

