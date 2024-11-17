package dev.theolm.pushalarm.ui.screens.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

internal class AddAlarmViewModel : ViewModel() {

    val uiState: State<UiState>
        field = mutableStateOf(UiState())

    fun onAppPackageChange(appPackage: String) {
        uiState.value = uiState.value.copy(appPackage = appPackage)
    }

    fun onNotificationTitleChange(notificationTitle: String) {
        uiState.value = uiState.value.copy(notificationTitle = notificationTitle)
    }

    data class UiState(
        val appPackage: String = "",
        val notificationTitle: String = "",
    )
}