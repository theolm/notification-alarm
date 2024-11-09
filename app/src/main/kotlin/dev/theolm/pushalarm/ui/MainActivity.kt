package dev.theolm.pushalarm.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.navigator.Navigator
import dev.theolm.pushalarm.ui.screens.home.HomeScreen
import dev.theolm.pushalarm.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()

        if (!isNotificationServiceEnabled()) {
            Toast.makeText(this, "Please enable notification access", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(intent)
        }

        setContent {
            AppTheme {
                Navigator(screen = HomeScreen())
            }
        }
    }

    private fun requestNotificationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // DO nothing
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                // Show rationale and request permission
                Toast.makeText(
                    this,
                    "Notification permission is required for this app to function properly",
                    Toast.LENGTH_LONG
                ).show()
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            else -> {
                // Directly request for permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            // TODO:W
           // enableNotificationService()
        } else {
            Toast.makeText(
                this,
                "Notification permission is required for this app to function properly",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}



private fun Context.isNotificationServiceEnabled(): Boolean {
    val pkgName: String = packageName
    val flat: String = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
    val names = flat.split(":".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()
    for (name in names) {
        val cn = ComponentName.unflattenFromString(name)
        if (cn != null) {
            if (pkgName == cn.packageName) {
                return true
            }
        }
    }
    return false
}
