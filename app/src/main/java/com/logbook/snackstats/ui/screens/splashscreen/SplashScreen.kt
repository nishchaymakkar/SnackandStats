package com.logbook.snackstats.ui.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.logbook.snackstats.R
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
fun SplashScreenPreview(){
    SnackStatsTheme {
        SplashScreen()
    }
}
@Composable
fun SplashScreen(
    onAppStart: (Any)-> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel : SplashScreenViewModel by inject(SplashScreenViewModel::class.java)

    LaunchedEffect(key1 = true) {
        viewModel.onAppStart(onAppStart)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
        content = {
            Column (modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource( R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold

                    )
                }
                Row {
                    Text(
                        text = stringResource(R.string.app_description),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    )

}