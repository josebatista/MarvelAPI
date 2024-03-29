package com.example.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toast: MutableLiveData<String> = MutableLiveData()

}