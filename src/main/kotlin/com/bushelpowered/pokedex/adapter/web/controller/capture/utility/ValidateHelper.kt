package com.bushelpowered.pokedex.adapter.web.controller.capture.utility

import org.springframework.stereotype.Component

@Component
class ValidateHelper {

    fun isNumber(num: String): Boolean {
        return try {
            num.toInt()
            true
        } catch (ex: Exception) {
            false
        }
    }

}