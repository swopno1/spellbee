package com.example.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/**
 * Centralized Google Mobile Ads (AdMob) Configuration for SpellBee.
 * Follows official Google Mobile Ads SDK guidelines and COPPA / Google Play Families Policy.
 */
object AdConfig {

    private const val TAG = "SpellBeeAdConfig"

    const val ADS_ENABLED = true

    // Configured AdMob Identifiers for SpellBee - Spelling Quiz
    const val APP_ID = "ca-app-pub-5222053984568989~4545635611"
    const val BANNER_AD_UNIT_ID = "ca-app-pub-5222053984568989/4396452645"
    const val INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-5222053984568989/4865199819"

    private var isInitialized = false
    private var mInterstitialAd: InterstitialAd? = null
    private var isInterstitialLoading = false

    fun initialize(context: Context) {
        if (isInitialized) return
        try {
            // Apply child-directed and family safety settings (COPPA)
            val requestConfiguration = RequestConfiguration.Builder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
                .build()
            MobileAds.setRequestConfiguration(requestConfiguration)

            MobileAds.initialize(context) { status ->
                isInitialized = true
                Log.d(TAG, "AdMob SDK Initialized successfully")
                // Preload first interstitial ad
                loadInterstitial(context.applicationContext)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing AdMob SDK", e)
        }
    }

    /**
     * Loads an Interstitial Ad ahead of time.
     */
    fun loadInterstitial(context: Context) {
        if (!ADS_ENABLED || isInterstitialLoading || mInterstitialAd != null) return

        isInterstitialLoading = true
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            INTERSTITIAL_AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    isInterstitialLoading = false
                    Log.d(TAG, "Interstitial Ad loaded successfully")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    mInterstitialAd = null
                    isInterstitialLoading = false
                    Log.w(TAG, "Interstitial Ad failed to load: ${loadAdError.message}")
                }
            }
        )
    }

    /**
     * Shows an Interstitial Ad at a natural break (e.g. after completing a quiz round).
     * Strictly complies with AdMob placement policy by never interrupting active gameplay.
     */
    fun showInterstitial(activity: Activity, onAdDismissed: () -> Unit) {
        if (!ADS_ENABLED) {
            onAdDismissed()
            return
        }

        val interstitial = mInterstitialAd
        if (interstitial != null) {
            interstitial.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Interstitial Ad dismissed by user")
                    mInterstitialAd = null
                    loadInterstitial(activity.applicationContext)
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.w(TAG, "Interstitial Ad failed to show: ${adError.message}")
                    mInterstitialAd = null
                    loadInterstitial(activity.applicationContext)
                    onAdDismissed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Interstitial Ad showed full screen content")
                }
            }
            interstitial.show(activity)
        } else {
            // Not ready yet, proceed immediately without blocking the user
            loadInterstitial(activity.applicationContext)
            onAdDismissed()
        }
    }
}

/**
 * Material 3 Banner Ad Component for SpellBee.
 * Positioned safely at the bottom navigation insets area.
 */
@Composable
fun SpellBeeBannerAd(
    modifier: Modifier = Modifier
) {
    if (!AdConfig.ADS_ENABLED) return

    val isPreview = LocalInspectionMode.current
    if (isPreview) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE2E8F0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AdMob Banner Preview",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
        }
        return
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = AdConfig.BANNER_AD_UNIT_ID
                adListener = object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.w("SpellBeeBannerAd", "Banner failed to load: ${loadAdError.message}")
                    }

                    override fun onAdLoaded() {
                        Log.d("SpellBeeBannerAd", "Banner loaded successfully")
                    }
                }
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
