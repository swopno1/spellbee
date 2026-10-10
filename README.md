# SpellBee 🐝

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2-purple.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-M3-blue.svg)](https://developer.android.com/jetpack/compose)

A lightweight, colorful, and engaging English spelling quiz Android application designed for children, students, and language learners.

Developed and published by **ViveScript Solutions LLC**  
Website: [https://www.vivescriptsolutions.com/](https://www.vivescriptsolutions.com/)  
Copyright © 2026 ViveScript Solutions LLC. All rights reserved.

---

## Overview

**SpellBee** makes practicing English spelling fun, bite-sized, and rewarding. Users are guided by **Barnaby the Bee**, an encouraging mascot, through interactive spelling quizzes that test commonly misspelled words, silent letters, and tricky double consonants.

The app is completely **offline-first**, requires **zero logins or accounts**, and stores user progress and high scores strictly on the local device.

---

## Key Features

- **Interactive Quizzes:** Multiple-choice word challenges and Honeycomb missing-letter puzzles.
- **On-Device Spoken Pronunciation:** Uses Android's native `TextToSpeech` engine to clearly pronounce words without external network calls.
- **Audio Speed Toggle:** Slow down speech playback to 0.75x to clearly distinguish syllables and subtle vowel sounds.
- **Spell Aloud Feature:** Tap to hear the app spell out words letter-by-letter.
- **Curated Word Bank:** 60+ tricky words divided into **Beginner Bee**, **Honey Worker (Intermediate)**, and **Queen Bee (Advanced)** levels, complete with phonetic guides, definitions, and mnemonic memory tips.
- **Streak Multipliers & Points:** Earn extra points for consecutive correct answers (x1.5 and x2.0 multipliers).
- **One-Tap Social Sharing:** Share high scores, streaks, and accuracy directly to friends on WhatsApp, Instagram Stories, TikTok, Telegram, and SMS via Android's native share menu.
- **Session Review:** Easily review every word encountered during a quiz session and replay audio pronunciations anytime.
- **AdMob Integration:** Fully configured with Google Mobile Ads SDK (`23.6.0`) with child-directed COPPA compliance flags.
  - **App ID:** `ca-app-pub-5222053984568989~4545635611`
  - **Banner 1:** `ca-app-pub-5222053984568989/4396452645`
  - **Interstitial 1:** `ca-app-pub-5222053984568989/4865199819`

---

## Technology Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Architecture:** MVVM (Model-View-ViewModel) + StateFlow
- **Speech Engine:** Android Native `android.speech.tts.TextToSpeech`
- **Data Storage:** Android `SharedPreferences` (offline, local-only)
- **Monetization SDK:** Google Mobile Ads SDK (`play-services-ads:23.6.0`)
- **Testing:** JUnit4, Robolectric

---

## Requirements

- **Minimum SDK:** API 24 (Android 7.0 Nougat)
- **Target SDK:** API 36 (Android 15+)
- **Build System:** Gradle with Kotlin DSL
- **JDK:** Java 11 / Java 17

---

## Installation & Development

### 1. Clone the Repository
```bash
git clone https://github.com/vivescriptsolutions/spellbee.git
cd spellbee
```

### 2. Open in Android Studio
- Open Android Studio (Ladybug or newer).
- Select **File > Open** and choose the `spellbee` directory.
- Allow Gradle to sync dependencies automatically.

### 3. Build & Run
- Connect an Android device or launch an Android Virtual Device (AVD).
- Run the `app` configuration or execute:
```bash
./gradlew installDebug
```

---

## Project Structure

```text
/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/
│   │   │   ├── MainActivity.kt
│   │   │   ├── ads/
│   │   │   │   └── AdConfig.kt                  # AdMob configuration & Banner composable
│   │   │   ├── data/
│   │   │   │   ├── SpellBeePreferences.kt       # Local offline persistence
│   │   │   │   └── WordBank.kt                  # Curated dictionary, definitions & tips
│   │   │   ├── ui/
│   │   │   │   ├── SpellBeeScreen.kt            # Compose UI for Home, Quiz & Results
│   │   │   │   ├── SpellBeeViewModel.kt         # Quiz state & scoring engine
│   │   │   │   └── theme/                       # Honey bee color palette & typography
│   │   │   └── util/
│   │   │       └── SpeechManager.kt             # Native Android TTS wrapper
│   │   ├── res/                                 # Drawables, mipmaps, strings
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── play_store_assets/                           # Google Play Store publishing assets
│   ├── app-icon/
│   ├── feature-graphic/
│   ├── phone/
│   └── tablet/
├── production_build/                            # Release App Bundle (.aab)
│   └── spellbee-release.aab
├── LICENSE                                      # MIT License + Branding Notice
├── PRIVACY_POLICY.md                            # Complete privacy policy
├── TERMS_OF_SERVICE.md                          # Application terms of service
├── PLAY_STORE_METADATA.md                       # Play Store title, descriptions, SEO/ASO
├── PLAY_STORE_DATA_SAFETY.md                    # Data Safety questionnaire guidance
└── DESIGN_ASSETS.md                             # Color palette and asset documentation
```

---

## Testing

Execute unit and Robolectric tests via Gradle:

```bash
gradle :app:testDebugUnitTest
```

Available test suites:
- `ExampleUnitTest.kt`: Validates WordBank integrity, distractors, definitions, and difficulty filtering.
- `ExampleRobolectricTest.kt`: Validates app string resources and offline stats storage in `SpellBeePreferences`.

---

## Privacy & Data Safety

SpellBee is designed with a strict privacy-first philosophy:
- **No User Accounts:** No personal identifiers, usernames, or passwords.
- **Local Persistence:** High scores, streaks, and settings are saved exclusively on the device.
- **No Microphone Access:** Speech output is synthesized locally by Android's TTS; no user voice is recorded.
- **Family Compliance:** Advertising configuration applies `TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE` and max content rating `G`.

---

## Licensing & Proprietary Notice

The source code of SpellBee is licensed under the [MIT License](LICENSE).

**Proprietary Notice:**  
© 2026 ViveScript Solutions LLC. The app name ("SpellBee"), cartoon mascot illustrations ("Barnaby the Bee"), logo, icons, screenshots, and associated branding are proprietary property of **ViveScript Solutions LLC** and are not licensed under the MIT License.
