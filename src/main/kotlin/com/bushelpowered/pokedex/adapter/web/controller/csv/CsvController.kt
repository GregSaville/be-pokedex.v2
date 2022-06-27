package com.bushelpowered.pokedex.adapter.web.controller.csv

import com.bushelpowered.pokedex.core.ingress.csv.CsvUseCase
import com.bushelpowered.pokedex.core.ingress.pokemon.FindPokemonUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/admin")
class CsvController(
    private val Csv: CsvUseCase,
    private val pokemonUseCase: FindPokemonUseCase
) {

    @PutMapping("/loadCSV")
    fun loadCSV(): ResponseEntity<String> {
        return if (pokemonUseCase.findById("1") == null) {
            Csv.loadCsv()
            ResponseEntity.ok("Loaded CSV")
        } else ResponseEntity.badRequest().body("CSV already Loaded")
    }

}

