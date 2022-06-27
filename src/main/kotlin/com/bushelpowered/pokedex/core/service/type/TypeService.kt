package com.bushelpowered.pokedex.core.service.type

import com.bushelpowered.pokedex.core.egress.type.TypePort
import com.bushelpowered.pokedex.core.ingress.type.FindTypeUseCase
import org.springframework.stereotype.Service

@Service
class TypeService(private val typePort: TypePort) : FindTypeUseCase {

    override fun findAll(): List<String> {
        val resultList = mutableListOf<String>()
        typePort.findAll().forEach {
            resultList.add(it.type)
        }
        return resultList.toList()
    }

}