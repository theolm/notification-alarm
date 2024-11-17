package dev.theolm.pushalarm.ui.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object HomeRoute : Destination

@Serializable
object AddAlarmRoute : Destination
