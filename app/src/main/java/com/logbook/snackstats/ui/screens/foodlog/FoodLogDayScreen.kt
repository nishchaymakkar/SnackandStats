package com.logbook.snackstats.ui.screens.foodlog

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.ui.components.MealCard
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
private fun FoodLogDayScreenPrev() {
    SnackStatsTheme {
        FoodLogDayScreen(
            selectedDate = System.currentTimeMillis()
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodLogDayScreen(
    modifier: Modifier = Modifier,
    selectedDate: Long
) {
    val formattedDate = remember(selectedDate) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(selectedDate?.let {
            Date(
                it
            )
        }!!)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = formattedDate
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },

    ){
        innerpadding ->
        Box(
            modifier = modifier.fillMaxSize().padding(innerpadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 16.dp)
            ){
                items(6){
                    MealCard()
                }
            }
        }
    }
}