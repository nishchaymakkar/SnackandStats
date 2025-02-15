package com.logbook.snackstats

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object LoginScreen

@Serializable
object HomeScreen

@Serializable
object FoodLogScreen

@Serializable
data class FoodLogDateScreen(
    val selectedDate: Long
)

@Serializable
object FoodLog

@Serializable
object SugarLog

@Serializable
object MainNavigation
@Serializable
object SugarLogScreen
@Serializable
object ProfileScreen

@Serializable
object AddFoodLogScreen