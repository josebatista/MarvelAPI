package com.example.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.model.Character
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel : BaseViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            if (loading.value == null) {
                loading.postValue(true)

                //TODO 1: Alterar pela consulta do repositorio

                loading.postValue(false)
            }
        }
    }
}
