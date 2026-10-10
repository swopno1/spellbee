# Google Play Data Safety Guidance — SpellBee

**Developer:** ViveScript Solutions LLC  
**Application ID:** `com.vivescriptsolutions.spellbee`  
**SDKs Used:** AndroidX, Jetpack Compose, Google Mobile Ads SDK (AdMob `23.6.0`)  

This document details the exact responses to select in the Google Play Console Data Safety questionnaire based on the actual codebase implementation.

---

## 1. Data Collection & Sharing Overview

| Question in Play Console | Recommended Selection | Explanation |
| :--- | :--- | :--- |
| **Does your app collect or share any of the required user data types?** | **Yes** (solely due to Google Mobile Ads SDK) | The app itself collects zero user data. The included Google Mobile Ads SDK may transmit non-personal diagnostic and advertising data. |
| **Is all of the user data collected by your app encrypted in transit?** | **Yes** | All network communication performed by Google Play Services / AdMob uses HTTPS / TLS encryption. |
| **Do you provide a way for users to request that their data be deleted?** | **Not Applicable / Yes** | The app stores zero user personal data on remote servers; all local stats can be wiped by clearing app cache/storage. |

---

## 2. Data Types Breakdown

### A. Personal Information
- **Name, Email, Phone, Address, User IDs:** **NO (Not collected, Not shared)**
- The app has no accounts, no authentication, and no profile system.

### B. Financial Information
- **Credit card, Bank details, Purchase history:** **NO (Not collected, Not shared)**
- The app has no in-app purchases or payment processing.

### C. Location
- **Precise Location:** **NO**
- **Approximate Location:** **YES / Shared** (by Google Mobile Ads SDK for ad serving purposes if enabled).

### D. App Info and Performance
- **Crash logs & Diagnostics:** **YES / Shared** (Google Mobile Ads SDK / Google Play Services collect anonymous diagnostic info for reliability and performance monitoring).

### E. Device or Other Identifiers
- **Advertising ID (AAID):** **YES / Shared** (by Google Mobile Ads SDK for advertising fraud prevention and frequency capping).

---

## 3. Children's & Families Policy Declarations

- **Target Age Groups:** All ages, including ages under 13.
- **Family Policy Compliance:** The application uses `setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)` and restricts ad content ratings to `G`.
- **Neutral Age Screen:** Not required as no personal data is collected or requested anywhere in the app.

---

## 4. Summary for Google Play Reviewers

SpellBee operates as an offline educational spelling quiz. High scores, streaks, and audio settings are retained purely on-device via SharedPreferences. No backend server, cloud database, or third-party analytical telemetry is implemented.
