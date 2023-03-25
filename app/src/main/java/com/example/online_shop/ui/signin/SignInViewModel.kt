package com.example.online_shop.ui.signin

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
class SignInViewModel @Inject constructor(
    private var dataRepository: DataRepository,
    private val errorApp: ErrorApp): ViewModel() {

    private var _checkPerson = MutableStateFlow<Boolean?>(null)
    var checkPerson = _checkPerson.asStateFlow()

    fun checkPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _checkPerson.value = null
                dataRepository.checkPerson(person)
            }.fold(
                onSuccess = { _checkPerson.value = it },
                onFailure = { errorApp.errorApi(it.message!!) }
            )
        }
    }
}