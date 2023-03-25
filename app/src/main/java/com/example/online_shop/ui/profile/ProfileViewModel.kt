package com.example.online_shop.ui.profile

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
class ProfileViewModel @Inject constructor(
    private var dataRepository: DataRepository,
    private val errorApp: ErrorApp): ViewModel() {

    private var _person = MutableStateFlow<Person?>(null)
    var person = _person.asStateFlow()

    fun setPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _person.value = null
                dataRepository.loginPerson(person)
            }.fold(
                onSuccess = { _person.value = it },
                onFailure = { errorApp.errorApi(it.message!!) }
            )
        }
    }

    fun savePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                dataRepository.savePerson(person)
            }.fold(
                onSuccess = {},
                onFailure = { errorApp.errorApi(it.message!!) }
            )
        }
    }
}