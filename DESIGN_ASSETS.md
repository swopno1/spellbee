# Design Assets Specification — SpellBee

**Published by:** ViveScript Solutions LLC  
**Application ID:** `com.vivescriptsolutions.spellbee`  
**Brand Identity:** Cheerful Bee & Meadow Theme  

---

## 1. Brand Visual Identity

SpellBee uses a warm, cheerful, and approachable visual identity inspired by cartoon honeybees and sunny flower meadows. The mascot is **Barnaby the Bee**, wearing a graduation cap and holding a yellow honey pencil.

### Color Palette

| Token | Hex Value | Semantic Usage |
| :--- | :--- | :--- |
| **Honey Gold** | `#F59E0B` | Primary actions, main play button, key badges |
| **Honey Amber** | `#D97706` | Secondary accents, audio speaker controls |
| **Honey Cream** | `#FEF3C7` | Background containers, streak badges, card highlights |
| **Honey Dark** | `#78350F` | Primary text and headlines |
| **Meadow Green** | `#10B981` | Correct answers, accuracy stars, share button |
| **Meadow Light** | `#D1FAE5` | Background for correct answer options |
| **Berry Red** | `#EF4444` | Incorrect answer indicator |
| **Berry Light** | `#FEE2E2` | Background for incorrect answer feedback |
| **Sky Blue** | `#0284C7` | Quiz prompts, word types, links |
| **Sky Blue Light**| `#E0F2FE` | Score pill backgrounds |
| **Slate Dark** | `#0F172A` | Dark mode background & contrast |
| **Card White** | `#FFFFFF` | Elevated surfaces in light mode |

---

## 2. Typography

- **Headlines & Numbers:** Bold, playful sans-serif (system sans with heavy weight and tabular figures where needed).
- **Body & Clues:** High-contrast sans-serif scaled with `.sp` units for full accessibility.

---

## 3. Launcher Icon System

- **Adaptive Foreground:** `res/drawable/ic_launcher_foreground.xml` (centers `img_mascot_bee` inside standard 66dp safe zone).
- **Adaptive Background:** `res/drawable/ic_launcher_background.xml` (solid `#FFC72C`).
- **Legacy Raster PNGs:** Generated for all mipmap densities:
  - `mipmap-mdpi` (48x48)
  - `mipmap-hdpi` (72x72)
  - `mipmap-xhdpi` (96x96)
  - `mipmap-xxhdpi` (144x144)
  - `mipmap-xxxhdpi` (192x192)
- **Round Icons:** `ic_launcher_round.png` circular masked PNGs across all densities for devices supporting `android:roundIcon`.

---

## 4. Google Play Publication Assets

- **High-Res App Icon:** `play_store_assets/app-icon/app-icon.png` (512x512, 32-bit PNG)
- **Feature Graphic:** `play_store_assets/feature-graphic/feature-graphic.png` (1024x500, PNG)
- **Phone Screenshots:** `play_store_assets/phone/phone-01-home.png`, `phone-02-quiz.png`, `phone-03-results.png`, `phone-04-review.png` (1080x1920)
- **Tablet Screenshots:** `play_store_assets/tablet/tablet-01.png`, `tablet-02.png` (1200x1920)
