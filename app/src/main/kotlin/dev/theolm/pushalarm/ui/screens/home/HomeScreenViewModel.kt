package dev.theolm.pushalarm.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

internal class HomeScreenViewModel: ViewModel() {
    val uiState = MutableStateFlow(State())

    data class State(
        val alarmTime: String = "",
    )
}