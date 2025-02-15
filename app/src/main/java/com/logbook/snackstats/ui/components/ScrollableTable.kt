package com.logbook.snackstats.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.ui.theme.SnackStatsTheme

@Preview
@Composable
private fun TablePrev() {
    SnackStatsTheme {
        val tableData = listOf(
            listOf("125", "167", "--", "--", "--", "--", "--", "--"),
            listOf("--", "--", "128", "245", "--", "--", "--", "--"),
            listOf("--", "--", "--", "--", "138", "180", "--", "--"),
            listOf("--", "--", "--", "--", "--", "167", "150", "--")
        )

        ScrollableTable(
            data = tableData
        )
    }

}

@Composable
fun ScrollableTable(data: List<List<String>>) {
    val headerRow = listOf("BB", "AB", "BL", "AL", "BD", "AD", "3:00 AM", "Other")
    val dateColumn = listOf("15 Jan 2025", "16 Jan 2025", "17 Jan 2025", "18 Jan 2025")

    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()

    Row (modifier = Modifier.fillMaxSize()) {
        // Fixed Date Column (Non-scrollable)
        Column(
            modifier = Modifier
                .verticalScroll(verticalScrollState)
                .background(Color.LightGray) // Highlight the fixed column
        ) {
            TableCell(text = "", isHeader = true) // Empty top-left corner
            dateColumn.forEach { date ->
                TableCell(text = date, isHeader = true)
            }
        }

        // Scrollable Data Table
        Column(
            modifier = Modifier
                .horizontalScroll(horizontalScrollState)
                .verticalScroll(verticalScrollState)
        ) {
            // Header Row (Scrollable)
            Row {
                headerRow.forEach { header ->
                    TableCell(text = header, isHeader = true)
                }
            }

            // Data Rows (Scrollable)
            data.forEach { rowData ->
                Row {
                    rowData.forEach { cellValue ->
                        TableCell(text = cellValue)
                    }
                }
            }
        }
    }
}

@Composable
fun TableCell(text: String, isHeader: Boolean = false) {
    val textColor = when {
        text == "--" -> Color.Gray
        text.toIntOrNull() != null && text.toInt() > 200 -> Color.Red
        text.toIntOrNull() != null -> Color.Green
        else -> Color.Black
    }

    Box(
        modifier = Modifier
            .border(1.dp, Color.Gray)
            .background(if (isHeader) Color.LightGray else Color.White)
            .padding(8.dp)
            .width(70.dp)
            .height(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
        )
    }
}
