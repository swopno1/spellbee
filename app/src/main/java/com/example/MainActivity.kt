package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ads.AdConfig
import com.example.ui.SpellBeeApp
import com.example.ui.SpellBeeViewModel
import com.example.ui.theme.SpellBeeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SpellBeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Google Mobile Ads SDK with COPPA/Families safe configuration
        AdConfig.initialize(this)

        setContent {
            SpellBeeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SpellBeeApp(viewModel = viewModel)
                }
            }
        }
    }
}
