package com.logbook.snackstats.ui.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.R
import com.logbook.snackstats.ui.components.AgeTextField
import com.logbook.snackstats.ui.components.BasicButton
import com.logbook.snackstats.ui.components.NameTextField
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
private fun SignUpScreenPreview() {
    SnackStatsTheme {
        SignUpScreen(onLoginClick = {})
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (Any)-> Unit
) {
    val viewModel : SignInViewModel by inject(SignInViewModel::class.java)
    val uiState by viewModel.uiState
    Scaffold (
        modifier
    ){  innerPadding ->        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    MaterialTheme
                        .colorScheme
                        .surface
                ),
            content = {
                Column (
                    modifier.fillMaxWidth()
                ){
                    Row(
                        modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                            .height(200.dp)
                    ){}

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = modifier.size(100.dp),
                            tint = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    }
                    Row (
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Welcome!",
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                    NameTextField(
                        value = uiState.userName,
                        onValueChange = viewModel::onUserNameChange,
                        imeAction = ImeAction.Next,
                        readOnly = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    AgeTextField(
                        readOnly = false,
                        value = uiState.userAge,
                        onValueChange = viewModel::onUserAgeChange,
                        imeAction = ImeAction.Done,
                        modifier = Modifier
                            .fillMaxWidth()
//                            .focusRequester(focusRequester)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    BasicButton(
                        onClick = {
                            viewModel.onLoginClick(onLoginClick)
                                  },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                    )


                }
            }
        )
        
    }
    
}