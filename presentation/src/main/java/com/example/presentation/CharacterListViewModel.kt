package com.example.presentation

import androidx.lifecycle.MutableLiveData
import com.example.data.model.Character
import com.example.presentation.base.BaseViewModel

class CharacterListViewModel : BaseViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    fun loadCharacters() {
        if (loading.value == null) {
            loading.postValue(true)

            //TODO 1: Alterar pela consulta do repositorio

            loading.postValue(false)
        }
    }
}
