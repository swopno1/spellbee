package com.example

import com.example.data.Difficulty
import com.example.data.WordBank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun wordBank_containsValidWordsAcrossDifficulties() {
        val words = WordBank.allWords
        assertTrue("WordBank should contain words", words.isNotEmpty())

        val beginnerWords = WordBank.getWordsByDifficulty(Difficulty.BEGINNER)
        val intermediateWords = WordBank.getWordsByDifficulty(Difficulty.INTERMEDIATE)
        val advancedWords = WordBank.getWordsByDifficulty(Difficulty.ADVANCED)

        assertTrue(beginnerWords.isNotEmpty())
        assertTrue(intermediateWords.isNotEmpty())
        assertTrue(advancedWords.isNotEmpty())
    }

    @Test
    fun wordBank_everyWordHasCorrectOptionAndValidDistractors() {
        WordBank.allWords.forEach { word ->
            assertTrue("Word '${word.word}' should have at least 3 options", word.options.size >= 3)
            assertTrue("Word '${word.word}' must include the correct spelling in options", word.options.contains(word.word))
            assertTrue("Word '${word.word}' must have a non-empty mnemonic tip", word.mnemonicTip.isNotBlank())
            assertTrue("Word '${word.word}' must have a definition", word.definition.isNotBlank())
        }
    }

    @Test
    fun wordBank_mixedDifficultyReturnsRandomizedPool() {
        val mixedWords = WordBank.getWordsByDifficulty(Difficulty.MIXED)
        assertEquals(WordBank.allWords.size, mixedWords.size)
    }
}
