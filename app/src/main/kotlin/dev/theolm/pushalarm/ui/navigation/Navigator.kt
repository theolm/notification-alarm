package dev.theolm.pushalarm.ui.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavigator = compositionLocalOf<NavHostController?> { null }