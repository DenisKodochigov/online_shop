package com.example.online_shop.ui

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyLifecycleObserver(private val registry: ActivityResultRegistry) : DefaultLifecycleObserver {

    private lateinit var launcher: ActivityResultLauncher<String>
    private var _uri = MutableStateFlow<Uri?>(null)
    var uri = _uri.asStateFlow()

    override fun onCreate(owner: LifecycleOwner) {
        launcher = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
            _uri.value = uri
        }
    }

    fun selectImage() {
        launcher.launch("image/*")
    }
}