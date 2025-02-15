@file:OptIn(ExperimentalLayoutApi::class, ExperimentalSharedTransitionApi::class)

package com.logbook.snackstats.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.R
import com.logbook.snackstats.ui.components.CircularIndicator
import com.logbook.snackstats.ui.components.ExpandableFab
import com.logbook.snackstats.ui.theme.SnackStatsTheme

@Preview
@Composable
private fun SharedTransitionScope.HomeScreenPreview() {
    SnackStatsTheme {
        AnimatedVisibility(
            visible = true
        ) {    HomeScreen(
            onFoodLogClick = {},
            onProfileClick = {},
            onSugarLogClick = {},
            onAddFoodLog = {},
            animatedVisibilityScope = this

        )}
      }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    modifier: Modifier = Modifier,
    onFoodLogClick : ()-> Unit,
    onSugarLogClick: ()-> Unit,
    onProfileClick: ()-> Unit,
    onAddFoodLog: ()-> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold (
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Welcome",modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = onProfileClick ,
                        content = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Person",
                                modifier = modifier.size(30.dp),

                            )
                        }
                    )
                },
                navigationIcon = {
                    Icon( painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp),

                         //   .padding(top = 20.dp)
                    )
                }


            )
        },
        floatingActionButton = {
            ExpandableFab(
                onAddSugarLog = {},
                onAddFoodLog = onAddFoodLog,
                animatedVisiblityScope = animatedVisibilityScope,
                modifier = modifier.padding(10.dp)
            )
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {

                CircularIndicator(
                    canvasSize = 150.dp,
                    indicatorValue = 80
                )
                HomeItemCard(
                    onClick = onFoodLogClick,
                    imageVector = Icons.Default.Fastfood,
                    text = "Food Log",
                    tint = MaterialTheme.colorScheme.tertiary
                )
                HomeItemCard(
                    onClick = onSugarLogClick,
                    imageVector = Icons.Default.Bloodtype,
                    text = "Sugar Log",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

    }

}

@Composable
fun HomeItemCard(
    onClick: ()-> Unit,
    imageVector: ImageVector,
    text: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Card (
        onClick = onClick, shape = RoundedCornerShape(15.dp),
        modifier = modifier.fillMaxWidth().height(200.dp).padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ){
        Box(Modifier.fillMaxSize(),
            content = {
              Column (
                  verticalArrangement = Arrangement.SpaceEvenly,
                  horizontalAlignment = Alignment.CenterHorizontally
              )  {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = tint
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            contentAlignment = Alignment.Center
        )
    }
    
}