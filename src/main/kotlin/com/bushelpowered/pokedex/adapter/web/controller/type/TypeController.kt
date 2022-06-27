package com.bushelpowered.pokedex.adapter.web.controller.type

import com.bushelpowered.pokedex.core.ingress.type.FindTypeUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/types")
class TypeController(private val findTypeUseCase: FindTypeUseCase) {

    @GetMapping("")
    fun getTypes(): ResponseEntity<List<String>> {
        val fetchList = findTypeUseCase.findAll()
        return if (fetchList.isNotEmpty()) {
            ResponseEntity.ok(fetchList)
        } else ResponseEntity.notFound().build()
    }
}