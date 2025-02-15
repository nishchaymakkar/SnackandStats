package com.logbook.snackstats.ui.screens.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logbook.snackstats.HomeScreen
import com.logbook.snackstats.LoginScreen
import com.logbook.snackstats.data.datastore.DataStoreRepository
import com.logbook.snackstats.ui.screens.home.HomeScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){

    val userName: Flow<String?> = dataStoreRepository.userName

    fun onAppStart(navigate: (Any) -> Unit){
        viewModelScope.launch {
            val userName1 = userName.firstOrNull()

            if (userName1 != null){
                navigate(HomeScreen)
            } else{
                navigate(LoginScreen)
            }
        }
    }
}