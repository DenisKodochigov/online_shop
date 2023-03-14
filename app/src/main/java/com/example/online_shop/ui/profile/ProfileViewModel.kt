package com.example.online_shop.ui.profile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online_shop.App
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
class ProfileViewModel @Inject constructor(): ViewModel() {
    private val dataRepository = DataRepository()

    private var _person = MutableStateFlow<Person?>(null)
    var person = _person.asStateFlow()

    fun loginPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _person.value = null
                dataRepository.loginPerson(person)
            }.fold(
                onSuccess = {
                    Log.d("KDS", "savePerson.loginPerson")
                    _person.value = it },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }

    fun saveImage(context: Context, imageBitmap: Bitmap, person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val fileNameImage = App.person.firstName.toString() + App.person.lastName.toString() + ".jpg"
                person.photo = fileNameImage
                dataRepository.saveImage(context, imageBitmap, fileNameImage)
            }.fold(
                onSuccess = {
                    Log.d("KDS", "ProfileViewModel.saveImage")
                    savePerson(person) },
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
    private fun savePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                dataRepository.savePerson(person)
            }.fold(
                onSuccess = {
                    Log.d("KDS", "savePerson.savePerson")
                    loginPerson(person)},
                onFailure = { ErrorApp().errorApi(it.message!!)}
            )
        }
    }
}