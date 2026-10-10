package com.example.data

import android.content.Context
import android.content.SharedPreferences

class SpellBeePreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("spellbee_prefs", Context.MODE_PRIVATE)

    var highScore: Int
        get() = prefs.getInt(KEY_HIGH_SCORE, 0)
        set(value) = prefs.edit().putInt(KEY_HIGH_SCORE, value).apply()

    var bestStreak: Int
        get() = prefs.getInt(KEY_BEST_STREAK, 0)
        set(value) = prefs.edit().putInt(KEY_BEST_STREAK, value).apply()

    var totalQuizzesPlayed: Int
        get() = prefs.getInt(KEY_TOTAL_QUIZZES, 0)
        set(value) = prefs.edit().putInt(KEY_TOTAL_QUIZZES, value).apply()

    var totalWordsCorrect: Int
        get() = prefs.getInt(KEY_TOTAL_CORRECT, 0)
        set(value) = prefs.edit().putInt(KEY_TOTAL_CORRECT, value).apply()

    var isAutoPlayAudioEnabled: Boolean
        get() = prefs.getBoolean(KEY_AUTO_PLAY_AUDIO, true)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_PLAY_AUDIO, value).apply()

    var isSlowSpeechEnabled: Boolean
        get() = prefs.getBoolean(KEY_SLOW_SPEECH, false)
        set(value) = prefs.edit().putBoolean(KEY_SLOW_SPEECH, value).apply()

    var isHapticEnabled: Boolean
        get() = prefs.getBoolean(KEY_HAPTIC, true)
        set(value) = prefs.edit().putBoolean(KEY_HAPTIC, value).apply()

    fun updateStats(score: Int, streak: Int, wordsCorrectCount: Int) {
        if (score > highScore) {
            highScore = score
        }
        if (streak > bestStreak) {
            bestStreak = streak
        }
        totalQuizzesPlayed += 1
        totalWordsCorrect += wordsCorrectCount
    }

    companion object {
        private const val KEY_HIGH_SCORE = "high_score"
        private const val KEY_BEST_STREAK = "best_streak"
        private const val KEY_TOTAL_QUIZZES = "total_quizzes"
        private const val KEY_TOTAL_CORRECT = "total_correct"
        private const val KEY_AUTO_PLAY_AUDIO = "auto_play_audio"
        private const val KEY_SLOW_SPEECH = "slow_speech"
        private const val KEY_HAPTIC = "haptic_enabled"
    }
}
