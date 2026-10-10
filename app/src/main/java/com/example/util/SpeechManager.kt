package com.example.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class SpeechManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false
    private var isSlowRate = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Fallback to default locale
                tts?.setLanguage(Locale.getDefault())
            }
            isInitialized = true
            updateSpeechRate()
        } else {
            Log.w("SpeechManager", "TTS initialization failed with status: $status")
        }
    }

    fun setSlowRate(slow: Boolean) {
        isSlowRate = slow
        updateSpeechRate()
    }

    private fun updateSpeechRate() {
        if (isInitialized) {
            tts?.setSpeechRate(if (isSlowRate) 0.75f else 1.0f)
            tts?.setPitch(1.05f) // Friendly cheerful tone for bee app
        }
    }

    fun speakWord(word: String) {
        if (isInitialized) {
            tts?.speak(word, TextToSpeech.QUEUE_FLUSH, null, "spellbee_word_$word")
        }
    }

    fun spellOutWord(word: String) {
        if (isInitialized) {
            val spacedLetters = word.uppercase().toCharArray().joinToString(separator = ", ")
            tts?.speak(spacedLetters, TextToSpeech.QUEUE_FLUSH, null, "spellbee_letters_$word")
        }
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("SpeechManager", "Error shutting down TTS", e)
        } finally {
            tts = null
            isInitialized = false
        }
    }
}
