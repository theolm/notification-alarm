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

internal class AlarmCoreImpl(
    private val applicationContext: Context,
    private val mediaPlayer: MediaPlayer,
) : AlarmCore {
    //TODO: replace with custom sound
    private val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

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

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
    }
}