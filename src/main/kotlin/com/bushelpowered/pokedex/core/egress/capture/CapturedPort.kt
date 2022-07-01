package com.bushelpowered.pokedex.core.egress.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured

interface CapturedPort : SaveCaptured

fun interface SaveCaptured {
    fun saveCaptured(captured: Captured): Boolean
}
