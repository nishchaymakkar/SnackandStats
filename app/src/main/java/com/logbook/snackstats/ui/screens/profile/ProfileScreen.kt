package com.logbook.snackstats.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.logbook.snackstats.ui.components.AgeTextField
import com.logbook.snackstats.ui.components.BasicButton
import com.logbook.snackstats.ui.components.NameTextField
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
private fun ProfileScreenPrev() {
    SnackStatsTheme {
        ProfileScreen(popUp = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    popUp: ()-> Unit,
    modifier: Modifier = Modifier) {

    val viewModel : ProfileViewModel by inject(ProfileViewModel::class.java)
    val userName by viewModel.userName.collectAsState()
    val userAge by viewModel.userAge.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile")
                },
                navigationIcon = {
                    IconButton(
                        onClick = popUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NameTextField(
                    value = "$userName" ?: "unknown",
                    onValueChange = {},
                    imeAction = ImeAction.None,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    readOnly = true
                )
                AgeTextField(
                    value = "$userAge" ?: "unknown",
                    onValueChange = {},
                    imeAction = ImeAction.None,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    readOnly = true
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 8.dp)
                ) {
                    Text(text = "Sign Out")
                }
            }
        }

    }
}