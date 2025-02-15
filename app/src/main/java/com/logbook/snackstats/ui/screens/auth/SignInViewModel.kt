package com.logbook.snackstats.ui.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logbook.snackstats.HomeScreen
import com.logbook.snackstats.data.datastore.DataStoreRepository
import com.logbook.snackstats.ui.screens.addfoodlog.AddFoodLogViewModel
import com.logbook.snackstats.ui.screens.auth.state.SignInState
import com.logbook.snackstats.ui.screens.profile.ProfileViewModel
import com.logbook.snackstats.ui.screens.splashscreen.SplashScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class SignInViewModel(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    var uiState = mutableStateOf(SignInState())
        private set

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState


    private val userName
    get() = uiState.value.userName

    private val userAge
        get() = uiState.value.userAge

    fun onUserNameChange(userName: String){
        uiState.value = uiState.value.copy(userName = userName)
    }

    fun onUserAgeChange(userAge: String){
        uiState.value = uiState.value.copy(userAge = userAge)
    }

    fun onLoginClick(navigate: (Any) -> Unit){
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            dataStoreRepository.saveUserCredentials(userName,userAge)
            _loginState.value = LoginUiState.Success
            navigate(HomeScreen)
        }
    }

}
sealed class LoginUiState{
    data object Idle: LoginUiState()
    data object Loading: LoginUiState()
    data object Success: LoginUiState()
    data class Error(val message: String): LoginUiState()
}

val viewModelModule = module {
    viewModel{ SignInViewModel(get()) }
    viewModel{SplashScreenViewModel(get())}
    viewModel{ ProfileViewModel(get()) }
    viewModel{AddFoodLogViewModel()}
}