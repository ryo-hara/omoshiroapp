package com.example.omoshiro_app.model

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class SoundRepository {

    private var soundPool: SoundPool? = null

    fun playSE(context: Context, fileId: Int){
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        if (soundPool == null){
            soundPool = SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build()
        }

        soundPool?.load(context, fileId, 1)?.also {
            soundPool?.setOnLoadCompleteListener{ soundPool, _, _ ->
                soundPool.play(it, 1.0f, 1.0f, 0, 0, 1.0f)
            }
        }
    }
}