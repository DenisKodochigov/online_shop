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
class LoginViewModel @Inject constructor(
    private var dataRepository: DataRepository,
    private val errorApp: ErrorApp): ViewModel() {

    private var _present = MutableStateFlow<Person?>(null)
    var present = _present.asStateFlow()


    fun loginPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _present.value = null
                dataRepository.loginPerson(person)
            }.fold(
                onSuccess = {
                    _present.value = it },
                onFailure = { errorApp.errorApi(it.message!!)}
            )
        }
    }
    fun presentToNull() {
        _present.value = null
    }
}