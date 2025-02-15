@file:OptIn(ExperimentalLayoutApi::class)

package com.logbook.snackstats.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.R
import com.logbook.snackstats.ui.theme.SnackStatsTheme

@Preview
@Composable
private fun MealCardPrev() {
    SnackStatsTheme {
        MealCard(

        )
    }
}

@Composable
fun MealCard(
    modifier: Modifier = Modifier
) {

    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ){
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Meal Image",
                    modifier = modifier.fillMaxWidth()
                        .aspectRatio(16/9f)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Row (modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween ){
                    Text(
                        text = "Meal Name",
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier
                       )
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Meal Time",
                            modifier = modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Meal Time",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                }
                FlowRow(
                    modifier = modifier.padding(8.dp).fillMaxWidth(),
                ) {
                    Text(
                        text = "Description",
                    )
                }
            }
        }

    }

}