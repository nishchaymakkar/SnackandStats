package com.logbook.snackstats.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.data.models.MealType

@ExperimentalMaterial3Api
@Composable
fun DropdownTextField(
    label: String,
    options: List<MealType>,
    onValueSelected: (MealType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var imeAction by remember { mutableStateOf(false) }
    // Outer Box to handle the ExposedDropdownMenu logic
    ExposedDropdownMenuBox (
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        // TextField to display selected value or hint
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { selectedOption = it },
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor(type= MenuAnchorType.PrimaryEditable, enabled= true) // Ensures alignment with the dropdown menu
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clickable { expanded = !expanded }, // Click on the field to show the dropdown
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = null
                )
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            )
        )

        // Dropdown menu with items
        ExposedDropdownMenu(
            shape = RoundedCornerShape(16.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.mealType) },
                    colors = MenuItemColors(textColor = MaterialTheme.colorScheme.onSurface,
                        leadingIconColor = MaterialTheme.colorScheme.onSurface,
                        trailingIconColor = MaterialTheme.colorScheme.onSurface,
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,),
                    onClick = {
                        imeAction = true
                        selectedOption = option.mealType
                        expanded = false
                        focusManager.moveFocus(
                            focusDirection = FocusDirection.Down
                        )
//                        onValueSelected(College(collegeName = option.collegeName, collegeId = option.collegeId, collegeAddress = option.collegeAddress, collegeImage = option.collegeImage)) // Pass the selected option to the parent composable
                    },modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                )
            }
        }
    }
}
