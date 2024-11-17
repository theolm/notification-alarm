package dev.theolm.pushalarm.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.pushalarm.R
import dev.theolm.pushalarm.ui.components.DefaultTopAppBar
import dev.theolm.pushalarm.ui.navigation.LocalNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddAlarmScreen(
    viewModel: AddAlarmViewModel = koinViewModel()
) {
    val navigator = LocalNavigator.current
    val scrollBarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBarBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(id = R.string.add_alarm_title),
                scrollBarBehavior = scrollBarBehavior,
                onBackPress = {
                    navigator?.popBackStack()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.largeShape,
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save filter"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add_alarm_message)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.uiState.value.appPackage,
                onValueChange = {
                    viewModel.onAppPackageChange(it)
                },
                label = { Text("App package (optional)") },
                supportingText = {
                    Text("Will trigger the alarm if notification has the provided app bundle. Example: com.whatsapp")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.uiState.value.notificationTitle,
                onValueChange = {
                    viewModel.onNotificationTitleChange(it)
                },
                label = { Text("Notification title (optional)") },
                supportingText = {
                    Text("Will trigger the alarm if notification has the provided title. Example: New message")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.uiState.value.notificationTitle,
                onValueChange = {
                    viewModel.onNotificationTitleChange(it)
                },
                label = { Text("Notification body (optional)") },
                supportingText = {
                    Text("Will trigger the alarm if notification has the provided body. Example: Hi there!")
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun AddAlarmScreenPreview() {
    AddAlarmScreen()
}