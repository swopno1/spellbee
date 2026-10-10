package com.example.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.data.Difficulty
import com.example.data.SpellBeePreferences
import com.example.data.SpellingWord
import com.example.data.WordBank
import com.example.util.SpeechManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class Screen {
    HOME,
    QUIZ,
    RESULTS
}

enum class QuizQuestionType {
    MULTIPLE_CHOICE,
    MISSING_LETTERS
}

data class QuizQuestion(
    val word: SpellingWord,
    val type: QuizQuestionType,
    val options: List<String>
)

data class AnsweredResult(
    val word: SpellingWord,
    val selectedAnswer: String,
    val isCorrect: Boolean,
    val pointsAwarded: Int
)

data class QuizUiState(
    val currentScreen: Screen = Screen.HOME,
    val selectedDifficulty: Difficulty = Difficulty.BEGINNER,
    val totalQuestionsPerQuiz: Int = 10,
    val currentQuestionIndex: Int = 0,
    val questions: List<QuizQuestion> = emptyList(),
    val score: Int = 0,
    val currentStreak: Int = 0,
    val highestStreakInSession: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerChecked: Boolean = false,
    val isCurrentAnswerCorrect: Boolean = false,
    val feedbackMessage: String = "",
    val answeredHistory: List<AnsweredResult> = emptyList(),
    // Preferences & Stats
    val highScore: Int = 0,
    val bestStreak: Int = 0,
    val totalQuizzesPlayed: Int = 0,
    val isAutoPlayAudio: Boolean = true,
    val isSlowSpeech: Boolean = false,
    val isReviewExpanded: Boolean = false
)

class SpellBeeViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = SpellBeePreferences(application)
    val speechManager = SpeechManager(application)

    private val _uiState = MutableStateFlow(
        QuizUiState(
            highScore = preferences.highScore,
            bestStreak = preferences.bestStreak,
            totalQuizzesPlayed = preferences.totalQuizzesPlayed,
            isAutoPlayAudio = preferences.isAutoPlayAudioEnabled,
            isSlowSpeech = preferences.isSlowSpeechEnabled
        )
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        speechManager.setSlowRate(preferences.isSlowSpeechEnabled)
    }

    fun setDifficulty(difficulty: Difficulty) {
        _uiState.update { it.copy(selectedDifficulty = difficulty) }
    }

    fun setQuestionCount(count: Int) {
        _uiState.update { it.copy(totalQuestionsPerQuiz = count) }
    }

    fun toggleAutoPlayAudio() {
        val newValue = !_uiState.value.isAutoPlayAudio
        preferences.isAutoPlayAudioEnabled = newValue
        _uiState.update { it.copy(isAutoPlayAudio = newValue) }
    }

    fun toggleSlowSpeech() {
        val newValue = !_uiState.value.isSlowSpeech
        preferences.isSlowSpeechEnabled = newValue
        speechManager.setSlowRate(newValue)
        _uiState.update { it.copy(isSlowSpeech = newValue) }
    }

    fun toggleReviewExpanded() {
        _uiState.update { it.copy(isReviewExpanded = !it.isReviewExpanded) }
    }

    fun startQuiz() {
        val pool = WordBank.getWordsByDifficulty(_uiState.value.selectedDifficulty)
        val selectedWords = pool.take(_uiState.value.totalQuestionsPerQuiz.coerceAtMost(pool.size))

        val generatedQuestions = selectedWords.mapIndexed { index, word ->
            // Alternate between multiple choice and missing letters for fun variety
            val type = if (index % 3 == 1) QuizQuestionType.MISSING_LETTERS else QuizQuestionType.MULTIPLE_CHOICE
            val options = when (type) {
                QuizQuestionType.MULTIPLE_CHOICE -> word.options.shuffled()
                QuizQuestionType.MISSING_LETTERS -> word.missingLetterOptions.shuffled()
            }
            QuizQuestion(word = word, type = type, options = options)
        }

        _uiState.update {
            it.copy(
                currentScreen = Screen.QUIZ,
                currentQuestionIndex = 0,
                questions = generatedQuestions,
                score = 0,
                currentStreak = 0,
                highestStreakInSession = 0,
                selectedAnswer = null,
                isAnswerChecked = false,
                isCurrentAnswerCorrect = false,
                feedbackMessage = "",
                answeredHistory = emptyList(),
                isReviewExpanded = false
            )
        }

        speakCurrentWord()
    }

    fun speakCurrentWord() {
        val currentQuestion = currentQuestion() ?: return
        speechManager.speakWord(currentQuestion.word.word)
    }

    fun spellOutCurrentWord() {
        val currentQuestion = currentQuestion() ?: return
        speechManager.spellOutWord(currentQuestion.word.word)
    }

    fun speakSpecificWord(word: String) {
        speechManager.speakWord(word)
    }

    fun selectAnswer(answer: String) {
        if (_uiState.value.isAnswerChecked) return // already submitted
        _uiState.update { it.copy(selectedAnswer = answer) }
        checkAnswer(answer)
    }

    private fun checkAnswer(answer: String) {
        val current = currentQuestion() ?: return
        val isCorrect = when (current.type) {
            QuizQuestionType.MULTIPLE_CHOICE -> answer.equals(current.word.word, ignoreCase = true)
            QuizQuestionType.MISSING_LETTERS -> answer.equals(current.word.correctMissingLetters, ignoreCase = true)
        }

        val basePoints = current.word.difficulty.pointsPerWord
        val streakMultiplier = when {
            _uiState.value.currentStreak >= 4 -> 2.0
            _uiState.value.currentStreak >= 2 -> 1.5
            else -> 1.0
        }

        val awardedPoints = if (isCorrect) (basePoints * streakMultiplier).toInt() else 0
        val newStreak = if (isCorrect) _uiState.value.currentStreak + 1 else 0
        val highestStreak = maxOf(_uiState.value.highestStreakInSession, newStreak)
        val newScore = _uiState.value.score + awardedPoints

        val feedback = if (isCorrect) {
            when {
                newStreak >= 5 -> "Un-bee-lievable! 5 In a Row! 🍯🐝 (+${awardedPoints} pts)"
                newStreak >= 3 -> "Sweet as Honey! Streak x1.5! 🔥 (+${awardedPoints} pts)"
                else -> "Buzz-tastic! Correct! 🐝 (+${awardedPoints} pts)"
            }
        } else {
            "Oops! The correct spelling is '${current.word.word}'. ${current.word.mnemonicTip}"
        }

        val historyItem = AnsweredResult(
            word = current.word,
            selectedAnswer = answer,
            isCorrect = isCorrect,
            pointsAwarded = awardedPoints
        )

        _uiState.update {
            it.copy(
                isAnswerChecked = true,
                isCurrentAnswerCorrect = isCorrect,
                score = newScore,
                currentStreak = newStreak,
                highestStreakInSession = highestStreak,
                feedbackMessage = feedback,
                answeredHistory = it.answeredHistory + historyItem
            )
        }
    }

    fun nextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex < _uiState.value.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswer = null,
                    isAnswerChecked = false,
                    isCurrentAnswerCorrect = false,
                    feedbackMessage = ""
                )
            }
            if (_uiState.value.isAutoPlayAudio) {
                speakCurrentWord()
            }
        } else {
            // Quiz Complete
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val finalScore = _uiState.value.score
        val bestStreakInQuiz = _uiState.value.highestStreakInSession
        val correctCount = _uiState.value.answeredHistory.count { it.isCorrect }

        preferences.updateStats(
            score = finalScore,
            streak = bestStreakInQuiz,
            wordsCorrectCount = correctCount
        )

        _uiState.update {
            it.copy(
                currentScreen = Screen.RESULTS,
                highScore = preferences.highScore,
                bestStreak = preferences.bestStreak,
                totalQuizzesPlayed = preferences.totalQuizzesPlayed
            )
        }
    }

    fun goToHome() {
        speechManager.stop()
        _uiState.update { it.copy(currentScreen = Screen.HOME) }
    }

    fun playAgain() {
        startQuiz()
    }

    fun createShareIntent(): Intent {
        val state = _uiState.value
        val correctCount = state.answeredHistory.count { it.isCorrect }
        val totalCount = state.questions.size
        val accuracyPct = if (totalCount > 0) (correctCount * 100) / totalCount else 0

        val shareText = buildString {
            appendLine("🐝 I just scored ${state.score} points on SpellBee! 🍯")
            appendLine("⭐ Accuracy: $accuracyPct% ($correctCount/$totalCount words)")
            appendLine("🔥 Best Streak: ${state.highestStreakInSession} in a row!")
            appendLine("Level: ${state.selectedDifficulty.label}")
            appendLine()
            appendLine("Can you beat my spelling buzz? 🏆")
            appendLine("Practice English spelling the fun way with SpellBee!")
            appendLine("#SpellBee #SpellingQuiz #LearnEnglish")
        }

        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "My SpellBee Quiz Score: ${state.score} pts!")
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
    }

    fun currentQuestion(): QuizQuestion? {
        val state = _uiState.value
        return state.questions.getOrNull(state.currentQuestionIndex)
    }

    override fun onCleared() {
        super.onCleared()
        speechManager.shutdown()
    }
}
