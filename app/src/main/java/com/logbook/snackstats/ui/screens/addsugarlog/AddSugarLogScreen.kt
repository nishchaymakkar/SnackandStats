@file:OptIn(ExperimentalMaterial3Api::class)

package com.logbook.snackstats.ui.screens.addsugarlog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.data.models.MealType
import com.logbook.snackstats.ui.components.DatePickerTextField
import com.logbook.snackstats.ui.components.DescriptionTextField
import com.logbook.snackstats.ui.components.DropdownTextField
import com.logbook.snackstats.ui.components.TimePickerTextField

@Composable
fun AddSugarLogScreen(
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ){ innerpadding->
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerpadding),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier.fillMaxSize()
            ) {



                DatePickerTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onDateSelected = {}
                )
                TimePickerTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onSelectedTime = {},

                    )

                DescriptionTextField(
                    modifier = modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = "",
                    onValueChange = {},
                    imeAction = ImeAction.Done,
                    readOnly = false
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)

                ) {
                    Text(
                        text = "Save"
                    )
                }


            }

        }
    }
}