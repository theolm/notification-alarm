package dev.theolm.pushalarm.models

import kotlinx.serialization.Serializable

@Serializable
data class AppLocalData(
    val version: Int = 1,
)