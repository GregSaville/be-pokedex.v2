package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.repositories.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(private val typeRepository: TypeRepository) {

    fun getAllTypes() : List<String>{
        val resultList = mutableListOf<String>()
        typeRepository.findAll().forEach{
            resultList.add(it.type)
        }
        return resultList.toList()
    }

}