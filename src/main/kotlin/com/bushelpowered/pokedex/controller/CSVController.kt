package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.CSVService
import com.bushelpowered.pokedex.utilites.CSVHelper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/admin")
class CSVController(private val helper: CSVHelper, private val CSVService: CSVService) {

    @PutMapping("/loadCSV")
    fun loadCSV(): ResponseEntity<String> {
        return try{ CSVService.loadCSV()
            ResponseEntity.ok("Loaded CSV")
        } catch (ex : Exception) {
            ResponseEntity.internalServerError().build()
        }
    }
}
