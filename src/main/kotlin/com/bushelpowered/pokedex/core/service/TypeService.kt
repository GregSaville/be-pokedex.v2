package com.bushelpowered.pokedex.core.service

import com.bushelpowered.pokedex.core.egress.TypePort
import com.bushelpowered.pokedex.core.ingress.FindTypeUseCase
import org.springframework.stereotype.Service

@Service
class TypeService(private val typePort: TypePort) : FindTypeUseCase{

    override fun findAll(): List<String> {
        val resultList = mutableListOf<String>()
        typePort.findAll().forEach{
            resultList.add(it.type)
        }
        return resultList.toList()
    }

}