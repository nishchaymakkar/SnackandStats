package com.logbook.snackstats

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.logbook.snackstats.ui.SnackStatsApp
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var showSplashScreen = true
//        actionBar?.hide()
        installSplashScreen().setKeepOnScreenCondition(
            condition = {
                showSplashScreen
            }
        )
        enableEdgeToEdge()
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            delay(1000)
            showSplashScreen = false
        }
        setContent {
            SnackStatsTheme {
                SnackStatsApp()

            }
        }
    }
}

