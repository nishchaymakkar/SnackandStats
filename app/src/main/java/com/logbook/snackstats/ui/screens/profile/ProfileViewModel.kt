package com.logbook.snackstats.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logbook.snackstats.SplashScreen
import com.logbook.snackstats.data.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    val userName: StateFlow<String?> = dataStoreRepository.userName
        .stateIn(viewModelScope,started = SharingStarted.WhileSubscribed(),null)
    val userAge : StateFlow<String?> = dataStoreRepository.userAge
        .stateIn(viewModelScope,started = SharingStarted.WhileSubscribed(),null)




    fun onSignOutClick(navigate: (Any) -> Unit){
        viewModelScope.launch {
            dataStoreRepository.clearCredentials()
        }
        navigate(SplashScreen)

    }
}