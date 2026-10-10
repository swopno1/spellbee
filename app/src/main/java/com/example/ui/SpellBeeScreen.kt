package com.example.ui

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ads.SpellBeeBannerAd
import com.example.data.Difficulty
import com.example.ui.theme.BerryRed
import com.example.ui.theme.BerryRedLight
import com.example.ui.theme.HoneyAmber
import com.example.ui.theme.HoneyCream
import com.example.ui.theme.HoneyDark
import com.example.ui.theme.HoneyGold
import com.example.ui.theme.MeadowGreen
import com.example.ui.theme.MeadowGreenLight
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueLight
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

@Composable
fun SpellBeeApp(viewModel: SpellBeeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            SpellBeeBannerAd(modifier = Modifier.navigationBarsPadding())
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFFBEB),
                            Color(0xFFFEF3C7).copy(alpha = 0.5f),
                            Color(0xFFFFFFFF)
                        )
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                when (uiState.currentScreen) {
                    Screen.HOME -> HomeScreen(viewModel = viewModel, uiState = uiState)
                    Screen.QUIZ -> QuizScreen(viewModel = viewModel, uiState = uiState)
                    Screen.RESULTS -> ResultsScreen(
                        viewModel = viewModel,
                        uiState = uiState,
                        onShare = {
                            val intent = viewModel.createShareIntent()
                            val chooser = Intent.createChooser(intent, "Share your SpellBee score via")
                            context.startActivity(chooser)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: SpellBeeViewModel, uiState: QuizUiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        // App Mascot & Header
        item {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(HoneyCream)
                    .border(3.dp, HoneyGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_bee),
                    contentDescription = "SpellBee Mascot Barnaby",
                    modifier = Modifier.size(115.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "SpellBee 🐝",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = HoneyDark
            )

            Text(
                text = "Buzz your way to perfect English spelling!",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = TextMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )
        }

        // Stats Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatItem(
                        icon = "🏆",
                        label = "High Score",
                        value = "${uiState.highScore} pts"
                    )
                    StatDivider()
                    StatItem(
                        icon = "🔥",
                        label = "Best Streak",
                        value = "${uiState.bestStreak}"
                    )
                    StatDivider()
                    StatItem(
                        icon = "🍯",
                        label = "Quizzes",
                        value = "${uiState.totalQuizzesPlayed}"
                    )
                }
            }
        }

        // Difficulty Selector
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Select Difficulty",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HoneyDark
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Difficulty.values().forEach { diff ->
                            val isSelected = uiState.selectedDifficulty == diff
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.setDifficulty(diff) },
                                label = { Text("${diff.icon} ${diff.name.take(4)}") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = HoneyGold,
                                    selectedLabelColor = Color.White
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("difficulty_${diff.name.lowercase()}")
                            )
                        }
                    }
                }
            }
        }

        // Question Count Selector
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Words per Quiz:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = HoneyDark
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(5, 10, 15).forEach { count ->
                            val isSelected = uiState.totalQuestionsPerQuiz == count
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) HoneyGold else Color(0xFFF1F5F9),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { viewModel.setQuestionCount(count) }
                                    .testTag("count_$count")
                            ) {
                                Text(
                                    text = "$count",
                                    color = if (isSelected) Color.White else TextSecondary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Start Quiz Button
        item {
            Button(
                onClick = { viewModel.startQuiz() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("play_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HoneyGold),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "START SPELLING QUIZ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Audio & Pronunciation settings row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { viewModel.toggleAutoPlayAudio() }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        tint = if (uiState.isAutoPlayAudio) HoneyAmber else TextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (uiState.isAutoPlayAudio) "Auto Pronounce: ON" else "Auto Pronounce: OFF",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { viewModel.toggleSlowSpeech() }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Speed,
                        contentDescription = null,
                        tint = if (uiState.isSlowSpeech) HoneyAmber else TextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (uiState.isSlowSpeech) "Audio Speed: 0.75x" else "Audio Speed: 1.0x",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel: SpellBeeViewModel, uiState: QuizUiState) {
    BackHandler {
        viewModel.goToHome()
    }

    val currentQ = viewModel.currentQuestion() ?: return
    val total = uiState.questions.size
    val currentNum = uiState.currentQuestionIndex + 1
    val progress = currentNum.toFloat() / total.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Top Quiz Bar
        TopAppBar(
            title = {
                Text(
                    text = "Word $currentNum of $total",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = HoneyDark
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { viewModel.goToHome() },
                    modifier = Modifier.testTag("quiz_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Exit Quiz",
                        tint = HoneyDark
                    )
                }
            },
            actions = {
                // Streak indicator
                if (uiState.currentStreak > 0) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = HoneyCream,
                        border = BorderStroke(1.dp, HoneyGold),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔥 ${uiState.currentStreak}", fontWeight = FontWeight.Bold, color = HoneyDark, fontSize = 13.sp)
                        }
                    }
                }
                // Score
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SkyBlueLight,
                    border = BorderStroke(1.dp, SkyBlue)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${uiState.score} pts",
                            fontWeight = FontWeight.ExtraBold,
                            color = SkyBlue,
                            fontSize = 14.sp
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )

        // Linear Progress
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = HoneyGold,
            trackColor = HoneyCream
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Word Clue & Audio Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Speaker pronunciation button
                        Button(
                            onClick = { viewModel.speakCurrentWord() },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = HoneyGold),
                            contentPadding = PaddingValues(16.dp),
                            modifier = Modifier
                                .size(68.dp)
                                .testTag("speak_word_button"),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Pronounce Word",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Tap to Hear Pronunciation",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = HoneyAmber
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Clue / Definition
                        Text(
                            text = "\"${currentQ.word.definition}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = HoneyDark,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Phonetic hint
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF1F5F9)
                        ) {
                            Text(
                                text = "Phonetic: ${currentQ.word.phonetic}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = TextSecondary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        // Question type prompt
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = when (currentQ.type) {
                                QuizQuestionType.MULTIPLE_CHOICE -> "Select the correct spelling:"
                                QuizQuestionType.MISSING_LETTERS -> "Complete the word:  ${currentQ.word.maskedWord}"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SkyBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Options List
            items(currentQ.options) { option ->
                val isSelected = uiState.selectedAnswer == option
                val isChecked = uiState.isAnswerChecked

                val isCorrectOption = when (currentQ.type) {
                    QuizQuestionType.MULTIPLE_CHOICE -> option.equals(currentQ.word.word, ignoreCase = true)
                    QuizQuestionType.MISSING_LETTERS -> option.equals(currentQ.word.correctMissingLetters, ignoreCase = true)
                }

                val backgroundColor by animateColorAsState(
                    targetValue = when {
                        !isChecked && isSelected -> HoneyCream
                        isChecked && isCorrectOption -> MeadowGreenLight
                        isChecked && isSelected && !isCorrectOption -> BerryRedLight
                        else -> Color.White
                    },
                    animationSpec = tween(250), label = "buttonColor"
                )

                val borderColor by animateColorAsState(
                    targetValue = when {
                        !isChecked && isSelected -> HoneyGold
                        isChecked && isCorrectOption -> MeadowGreen
                        isChecked && isSelected && !isCorrectOption -> BerryRed
                        else -> Color(0xFFE2E8F0)
                    },
                    animationSpec = tween(250), label = "borderColor"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(enabled = !isChecked) {
                            viewModel.selectAnswer(option)
                        }
                        .testTag("option_$option"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor),
                    border = BorderStroke(2.dp, borderColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                isChecked && isCorrectOption -> MeadowGreen
                                isChecked && isSelected && !isCorrectOption -> BerryRed
                                else -> Color(0xFF1E293B)
                            }
                        )

                        if (isChecked) {
                            if (isCorrectOption) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = MeadowGreen,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Incorrect",
                                    tint = BerryRed,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Feedback Card & Next Button
            item {
                AnimatedVisibility(
                    visible = uiState.isAnswerChecked,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 16.dp)
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (uiState.isCurrentAnswerCorrect) MeadowGreenLight else BerryRedLight
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = uiState.feedbackMessage,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (uiState.isCurrentAnswerCorrect) Color(0xFF065F46) else Color(0xFF991B1B)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedButton(
                                        onClick = { viewModel.spellOutCurrentWord() },
                                        modifier = Modifier.testTag("spell_out_button"),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Spell Aloud 🔊", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }

                                    Button(
                                        onClick = { viewModel.nextQuestion() },
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = HoneyGold),
                                        modifier = Modifier.testTag("next_word_button")
                                    ) {
                                        Text(
                                            text = if (currentNum < total) "Next Word ➡️" else "View Results 🏆",
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultsScreen(
    viewModel: SpellBeeViewModel,
    uiState: QuizUiState,
    onShare: () -> Unit
) {
    BackHandler {
        viewModel.goToHome()
    }

    val context = LocalContext.current

    val correctCount = uiState.answeredHistory.count { it.isCorrect }
    val totalCount = uiState.questions.size
    val accuracyPct = if (totalCount > 0) (correctCount * 100) / totalCount else 0

    val stars = when {
        accuracyPct >= 90 -> 3
        accuracyPct >= 65 -> 2
        else -> 1
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item {
            // Bee Mascot with Trophy
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(HoneyCream)
                    .border(3.dp, HoneyGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_bee),
                    contentDescription = "Mascot",
                    modifier = Modifier.size(95.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Stars
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                repeat(3) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < stars) HoneyGold else Color(0xFFCBD5E1),
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            Text(
                text = when (stars) {
                    3 -> "Un-bee-lievable! 🐝✨"
                    2 -> "Buzz-tastic Job! 🍯"
                    else -> "Great Effort! Keep Buzzing! 🌱"
                },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = HoneyDark,
                textAlign = TextAlign.Center
            )

            Text(
                text = "You scored ${uiState.score} points!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SkyBlue,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )
        }

        // Performance Summary Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Quiz Summary",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HoneyDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        SummaryBadge(label = "Accuracy", value = "$accuracyPct%")
                        SummaryBadge(label = "Correct", value = "$correctCount / $totalCount")
                        SummaryBadge(label = "Best Streak", value = "${uiState.highestStreakInSession} 🔥")
                    }
                }
            }
        }

        // Action Buttons: Share Score & Play Again
        item {
            // One-Tap Social Share
            Button(
                onClick = onShare,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("share_score_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MeadowGreen),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SHARE SCORE TO FRIENDS",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Play Again
            Button(
                onClick = {
                    val activity = context as? android.app.Activity
                    if (activity != null) {
                        com.example.ads.AdConfig.showInterstitial(activity) {
                            viewModel.playAgain()
                        }
                    } else {
                        viewModel.playAgain()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("play_again_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HoneyGold),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "PLAY AGAIN",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Back to Home
            OutlinedButton(
                onClick = {
                    val activity = context as? android.app.Activity
                    if (activity != null) {
                        com.example.ads.AdConfig.showInterstitial(activity) {
                            viewModel.goToHome()
                        }
                    } else {
                        viewModel.goToHome()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("home_button"),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, HoneyAmber)
            ) {
                Text(
                    text = "BACK TO HOME",
                    fontWeight = FontWeight.Bold,
                    color = HoneyDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Review Words Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.toggleReviewExpanded() },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Review Session Words (${uiState.answeredHistory.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = HoneyDark
                        )
                        Text(
                            text = if (uiState.isReviewExpanded) "Hide ▲" else "Show ▼",
                            color = SkyBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (uiState.isReviewExpanded) {
                        Spacer(modifier = Modifier.height(12.dp))
                        uiState.answeredHistory.forEach { item ->
                            ReviewWordRow(item = item, onSpeak = { viewModel.speakSpecificWord(item.word.word) })
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewWordRow(item: AnsweredResult, onSpeak: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (item.isCorrect) MeadowGreenLight.copy(alpha = 0.5f) else BerryRedLight.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, if (item.isCorrect) MeadowGreen else BerryRed),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.word.word,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.word.phonetic,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted
                    )
                }
                Text(
                    text = item.word.definition,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary,
                    maxLines = 2
                )
                if (!item.isCorrect) {
                    Text(
                        text = "Tip: ${item.word.mnemonicTip}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF991B1B)
                    )
                }
            }

            IconButton(
                onClick = onSpeak,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("review_speak_${item.word.word}")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Speak ${item.word.word}",
                    tint = HoneyDark
                )
            }
        }
    }
}

@Composable
fun StatItem(icon: String, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 20.sp)
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = HoneyDark
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = TextMuted
        )
    }
}

@Composable
fun StatDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(36.dp)
            .background(Color(0xFFE2E8F0))
    )
}

@Composable
fun SummaryBadge(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = HoneyDark
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = TextMuted
        )
    }
}
