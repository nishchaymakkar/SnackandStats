@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.logbook.snackstats.ui.screens.sugarlog

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import java.util.Date
import java.util.Locale

@Preview
@Composable
private fun SugarLogPrev() {
    SnackStatsTheme {
        SugarLogScreen()
    }
}

@Composable
fun SugarLogScreen(
    modifier: Modifier = Modifier
) {
    val currentMillis = remember { System.currentTimeMillis() }

    // Initialize DatePickerState with the current date
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentMillis)

    val selectedDate = datePickerState.selectedDateMillis?.let {
        Calendar.getInstance().apply { timeInMillis = it }
    }


    Scaffold { innerPadding->
        Box(
            modifier.padding(innerPadding)
        ) {
            DatePicker(
                state = datePickerState, // Hides main date headline
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedYearContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }

    }
}