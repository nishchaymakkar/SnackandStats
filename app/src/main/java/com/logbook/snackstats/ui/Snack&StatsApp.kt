@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.logbook.snackstats.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.logbook.snackstats.AddFoodLogScreen
import com.logbook.snackstats.FoodLog
import com.logbook.snackstats.FoodLogDateScreen
import com.logbook.snackstats.FoodLogScreen
import com.logbook.snackstats.HomeScreen
import com.logbook.snackstats.LoginScreen
import com.logbook.snackstats.ProfileScreen
import com.logbook.snackstats.SplashScreen
import com.logbook.snackstats.SugarLog
import com.logbook.snackstats.SugarLogScreen
import com.logbook.snackstats.ui.screens.addfoodlog.AddFoodLogScreen
import com.logbook.snackstats.ui.screens.foodlog.FoodLogDayScreen
import com.logbook.snackstats.ui.screens.foodlog.FoodLogScreen
import com.logbook.snackstats.ui.screens.home.HomeScreen
import com.logbook.snackstats.ui.screens.auth.SignUpScreen
import com.logbook.snackstats.ui.screens.profile.ProfileScreen
import com.logbook.snackstats.ui.screens.splashscreen.SplashScreen
import com.logbook.snackstats.ui.screens.sugarlog.SugarLogScreen
const val FAB_EXPLODE_BOUNDS_KEY = "FAB_EXPLODE_BOUNDS_KEY"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SnackStatsApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold {innerPadding ->
        SharedTransitionLayout {
            NavHost(navController = navController,
                modifier =   modifier,
                startDestination = SplashScreen
            ){
                composable<SplashScreen>{
                    SplashScreen(
                        onAppStart = { navigate ->
                            navController.navigate(navigate){
                                launchSingleTop = true
                                popUpTo(0) {
                                    inclusive = true
                                }
                            } }
                    )
                }
                composable<LoginScreen>{
                    SignUpScreen(
                        onLoginClick = {
                                navigate -> navController.navigate(navigate){
                            launchSingleTop = true
                            popUpTo(0){
                                inclusive = true
                            }
                        }
                        }
                    )
                }

                composable<HomeScreen>(
//                enterTransition = { scaleIn() },
//                exitTransition = {
//                    scaleOut()
//                },
                    popExitTransition = { scaleOut() },
                    popEnterTransition = { scaleIn() }
                ){
                    HomeScreen(
                        onFoodLogClick = { navController.navigate(FoodLog) },
                        onProfileClick = { navController.navigate(ProfileScreen) },
                        onSugarLogClick = { navController.navigate(SugarLog) },
                        onAddFoodLog = {navController.navigate(AddFoodLogScreen)},
                        modifier = Modifier,
                        animatedVisibilityScope = this
                    )
                }
                navigation<FoodLog>(
                    startDestination = FoodLogScreen,
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(700)

                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(700)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(700)
                        )

                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(700)
                        )
                    }
                ){
                    composable<FoodLogScreen> {
                        FoodLogScreen(
                            onDateSelected = {selectedDate -> navController.navigate(FoodLogDateScreen(selectedDate))}
                        )

                    }
                    composable<FoodLogDateScreen> { backStackEntry ->
                        val selectedDate = backStackEntry.arguments?.getLong("selectedDate")

                        FoodLogDayScreen(
                            selectedDate = selectedDate!!
                        )
                    }
                }

                navigation<SugarLog>(
                    startDestination = SugarLogScreen
                ){
                    composable<SugarLogScreen> {
                        SugarLogScreen()
                    }
                }
                composable<ProfileScreen>(
                    enterTransition = { slideInHorizontally{it}},
                    exitTransition = {  slideOutHorizontally { it }} ,
                    popEnterTransition = {
                        slideInHorizontally { it }  // Slide in from left when going back
                    },
                    popExitTransition = {
                        slideOutHorizontally { it }  // Slide out to right when going back
                    }
                ) {
                    ProfileScreen(popUp = {navController.popBackStack()})

                }

                composable<AddFoodLogScreen> {

                    AddFoodLogScreen(
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(
                                key = FAB_EXPLODE_BOUNDS_KEY
                            ),
                            animatedVisibilityScope = this
                        )
                    )

                }
            }
        }


    }

}