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
class SignInViewModel  @Inject constructor(): ViewModel() {
    private val dataRepository = DataRepository()
    //Data chanel for images
    private var _checkPerson = MutableStateFlow<Boolean>(false)
    var checkPerson = _checkPerson.asStateFlow()

    fun checkPerson(firstName:String, lastName:String, password: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
//                _checkPerson.value = null
                dataRepository.checkPerson(firstName, lastName, password, email)
            }.fold(
                onSuccess = {_checkPerson.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
}