package dev.theolm.pushalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.theolm.pushalarm.alarm.AlarmCore
import dev.theolm.pushalarm.ui.theme.AppTheme
import org.koin.compose.koinInject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Body()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Body(
    alarmCore: AlarmCore = koinInject()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notification Alarm",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            Button(
                onClick = {
                    alarmCore.start()
                }
            ) {
                Text(text = "Ring Alarm")
            }

            Button(
                onClick = {
                    alarmCore.stop()
                }
            ) {
                Text(text = "Stop Alarm")
            }
        }
    }
}
