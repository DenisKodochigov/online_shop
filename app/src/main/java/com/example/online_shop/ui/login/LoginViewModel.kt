package com.example.online_shop.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online_shop.data.DataRepository
import com.example.online_shop.entity.ErrorApp
import com.example.online_shop.entity.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    private val dataRepository = DataRepository()

    private var _loginPerson = MutableStateFlow<Person?>(null)
    var loginPerson = _loginPerson.asStateFlow()


    fun loginPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _loginPerson.value = null
                dataRepository.loginPerson(person)
            }.fold(
                onSuccess = {
                    _loginPerson.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
    fun presentToNull() {
        _loginPerson.value = null
    }
}