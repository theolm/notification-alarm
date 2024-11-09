package dev.theolm.pushalarm.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import dev.theolm.pushalarm.ui.MainActivity
import dev.theolm.pushalarm.R
import dev.theolm.pushalarm.alarm.AlarmCore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val ChannelId = "Mathias123"
private const val ChannelName = "Notification Listener Service Channel"
private const val ForegroundServiceId = 666
private const val NotificationRequestCode = 32189
class NotificationListener : NotificationListenerService(), KoinComponent {
    private val alarmCore by inject<AlarmCore>()
    private val notification by lazy { buildNotification() }

    override fun onCreate() {
        super.onCreate()
        startForeground(ForegroundServiceId, notification)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)

        val packageName = sbn.packageName
        val notificationTitle = sbn.notification.extras.getString("android.title")
        val notificationText = sbn.notification.extras.getString("android.text")

        Log.d("NotificationListener", "Notification Posted: $packageName : $notificationTitle : $notificationText")

        if (packageName == "com.freestylelibre.app.br") {
            alarmCore.start()
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Log.d("NotificationListener", "Notification Removed: ${sbn.packageName}")
    }

    private fun buildNotification() : Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ChannelId,
                ChannelName,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            NotificationRequestCode,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        return NotificationCompat.Builder(this, ChannelId)
            .setContentTitle("Notification Listener Service")
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSilent(true)
            .setOngoing(true)
            .build()
    }
}