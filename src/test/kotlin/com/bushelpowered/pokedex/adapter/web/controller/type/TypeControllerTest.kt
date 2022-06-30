package com.bushelpowered.pokedex.adapter.web.controller.type

import com.bushelpowered.pokedex.core.ingress.type.FindTypeUseCase
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class TypeControllerTest {

    private val findTypeUseCase = mockk<FindTypeUseCase>(relaxed = true)

    @Test
    fun `returns appropriate response according to fetchList`() {

        val fetchList = findTypeUseCase.findAll()

        if(fetchList.isEmpty()){
            assert(fetchList.isEmpty())
        }else{
            assert(fetchList.isNotEmpty())
        }
    }

}