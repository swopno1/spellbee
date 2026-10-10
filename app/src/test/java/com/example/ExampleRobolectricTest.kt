package com.example

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.example.ads.AdConfig
import com.example.data.Difficulty
import com.example.data.SpellBeePreferences
import com.example.data.WordBank
import com.example.ui.QuizQuestionType
import com.example.ui.Screen
import com.example.ui.SpellBeeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun appName_equalsSpellBee() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("SpellBee", appName)
    }

    @Test
    fun spellBeePreferences_savesAndRetrievesStats() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = SpellBeePreferences(context)

        prefs.updateStats(score = 850, streak = 7, wordsCorrectCount = 8)

        assertEquals(850, prefs.highScore)
        assertEquals(7, prefs.bestStreak)
        assertTrue(prefs.totalQuizzesPlayed >= 1)
        assertTrue(prefs.totalWordsCorrect >= 8)
    }

    @Test
    fun adConfig_identifiersAreConfiguredProperly() {
        assertTrue(AdConfig.APP_ID.startsWith("ca-app-pub-"))
        assertTrue(AdConfig.BANNER_AD_UNIT_ID.startsWith("ca-app-pub-"))
        assertTrue(AdConfig.INTERSTITIAL_AD_UNIT_ID.startsWith("ca-app-pub-"))
        assertTrue(AdConfig.ADS_ENABLED)
    }

    @Test
    fun viewModel_quizFlowAndScoringExecution() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = SpellBeeViewModel(app)

        // Initial state
        assertEquals(Screen.HOME, viewModel.uiState.value.currentScreen)
        viewModel.setDifficulty(Difficulty.BEGINNER)
        viewModel.setQuestionCount(5)

        // Start Quiz
        viewModel.startQuiz()
        assertEquals(Screen.QUIZ, viewModel.uiState.value.currentScreen)
        assertEquals(5, viewModel.uiState.value.questions.size)
        assertEquals(0, viewModel.uiState.value.score)
        assertEquals(0, viewModel.uiState.value.currentStreak)

        // Answer Question 1 correctly
        val q1 = viewModel.currentQuestion()
        assertNotNull(q1)
        val correctAns1 = when (q1!!.type) {
            QuizQuestionType.MULTIPLE_CHOICE -> q1.word.word
            QuizQuestionType.MISSING_LETTERS -> q1.word.correctMissingLetters
        }
        viewModel.selectAnswer(correctAns1)

        assertTrue(viewModel.uiState.value.isAnswerChecked)
        assertTrue(viewModel.uiState.value.isCurrentAnswerCorrect)
        assertEquals(1, viewModel.uiState.value.currentStreak)
        assertTrue(viewModel.uiState.value.score > 0)

        // Next Question
        viewModel.nextQuestion()
        assertEquals(1, viewModel.uiState.value.currentQuestionIndex)
        assertFalse(viewModel.uiState.value.isAnswerChecked)

        // Share intent creation verification
        val shareIntent = viewModel.createShareIntent()
        assertEquals(Intent.ACTION_SEND, shareIntent.action)
        assertEquals("text/plain", shareIntent.type)
        val shareText = shareIntent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
        assertTrue(shareText.contains("SpellBee"))
        assertTrue(shareText.contains("#SpellBee"))
    }
}
