package dev.theolm.pushalarm.alarm

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build

interface AlarmCore {
    fun start()
    fun stop()
}

internal class AlarmCoreImpl(private val applicationContext: Context) : AlarmCore {
    //TODO: replace with custom sound
    private val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    private val mediaPlayer = MediaPlayer()

    init {
        setupMediaPlayer()
    }

    override fun start() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    override fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun setupMediaPlayer() {
        mediaPlayer.setDataSource(applicationContext, alert)
        mediaPlayer.isLooping = true

        if (Build.VERSION.SDK_INT >= 21) {
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
        } else {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM)
        }
    }
}