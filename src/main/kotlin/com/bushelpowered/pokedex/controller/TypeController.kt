package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.TypeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/types")
class TypeController(private val typeService: TypeService) {

    @GetMapping("")
    fun getTypes(): ResponseEntity<List<String>> {
        val fetchList = typeService.getAllTypes()
        return if (fetchList.isNotEmpty()) {
            ResponseEntity.ok(fetchList)
        } else ResponseEntity.notFound().build()
    }
}