package com.example.online_shop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online_shop.data.DataRepository
import com.example.online_shop.data.api.FlashDTO
import com.example.online_shop.data.api.LatestDTO
import com.example.online_shop.data.api.ProductDTO
import com.example.online_shop.data.api.ProductDiscountDTO
import com.example.online_shop.entity.ErrorApp
import com.example.online_shop.entity.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val dataRepository = DataRepository()

    private var _personGet = MutableStateFlow<Person?>(null)
    var personGet = _personGet.asStateFlow()
    private var _latest = MutableStateFlow<LatestDTO?>(null)
    var latest = _latest.asStateFlow()
    private var _flash = MutableStateFlow<FlashDTO?>(null)
    var flash = _flash.asStateFlow()
    private var _brands = MutableStateFlow<LatestDTO?>(null)
    var brands = _brands.asStateFlow()

    fun loginPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _personGet.value = null
                dataRepository.loginPerson(person)
            }.fold(
                onSuccess = { _personGet.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
    fun getLatestAndFlash(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                dataRepository.getLatest(person)
            }.fold(
                onSuccess = { _latest.value = it
                            },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
    fun getFlash(){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                dataRepository.getFlash()
            }.fold(
                onSuccess = { _flash.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
    fun getBrands(){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                dataRepository.getBrands()
            }.fold(
                onSuccess = { _brands.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
}