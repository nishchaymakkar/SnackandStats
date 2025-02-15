@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.logbook.snackstats.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.ui.FAB_EXPLODE_BOUNDS_KEY

@Composable
fun SharedTransitionScope.ExpandableFab(
    onAddFoodLog: () -> Unit,
    onAddSugarLog: () -> Unit,
    modifier: Modifier,
    animatedVisiblityScope : AnimatedVisibilityScope
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedVisibility(visible = expanded) {
                Column(horizontalAlignment = Alignment.End) {
                    ExtendedFloatingActionButton(
                        modifier = modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(
                                key = FAB_EXPLODE_BOUNDS_KEY
                            ),
                    animatedVisibilityScope = animatedVisiblityScope
                    ),
                        onClick = { onAddFoodLog() },
                        containerColor = Color(0xFF4CAF50),
                        icon = {Icon(Icons.Default.Restaurant, contentDescription = "Add Food Log")},
                        text = {Text("Add Food Log")}// Green for food log
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ExtendedFloatingActionButton(
                        onClick = { onAddSugarLog() },
                        containerColor = Color(0xFFFF5722),
                        icon = {Icon(Icons.Default.MedicalServices, contentDescription = "Add Sugar Log")},
                        text = {Text("Add Sugar Log")}// Red for sugar log
                    )
                }
            }

            FloatingActionButton(
                onClick = { expanded = !expanded },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer // Blue for main FAB
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = "Expand"
                )
            }
        }
    }
}
