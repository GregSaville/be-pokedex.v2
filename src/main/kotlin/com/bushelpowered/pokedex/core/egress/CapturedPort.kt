package com.bushelpowered.pokedex.core.egress

import com.bushelpowered.pokedex.adapter.persistence.entities.Captured

interface CapturedPort : SaveCaptured


fun interface SaveCaptured{
    fun saveCaptured(captured: Captured) : Boolean
}
