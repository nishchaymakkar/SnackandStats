@file:OptIn(ExperimentalMaterial3Api::class)

package com.logbook.snackstats.ui.screens.foodlog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import java.util.Calendar
import java.util.Locale

@Preview
@Composable
private fun FoodLogScreenPreview() {
    SnackStatsTheme {
        FoodLogScreen(
            onDateSelected = {}
        )
    }
}

@Composable
fun FoodLogScreen(
    modifier: Modifier = Modifier,
    onDateSelected: (Long) -> Unit
 ) {
    val todayMillis = remember { System.currentTimeMillis() }
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = todayMillis,
        yearRange = IntRange(2000, currentYear),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= todayMillis
            }
        }
    )
    Scaffold {innerpadding ->
        Box(
            modifier = modifier.padding(innerpadding),
            contentAlignment = Alignment.Center
        ) {
            Column {


                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        headlineContentColor = MaterialTheme.colorScheme.onSurface,
                        selectedDayContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedYearContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedYearContentColor = MaterialTheme.colorScheme.onPrimary,

                        ),


                    )
               HorizontalDivider()
                Row (
                    modifier = modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.End
                ){
                    Button(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { selectedDate ->
                                onDateSelected(selectedDate) // Navigate when the button is clicked
                            }
                        },
                        enabled = datePickerState.selectedDateMillis != null // Enable only if a date is selected
                    ) {
                        Text("Confirm Date")
                    }
                }
            }
        }

    }
}