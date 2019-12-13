package com.example.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.model.Character
import com.example.data_api.MarvelApiRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel : BaseViewModel() {

    private val repo: Repository = MarvelApiRepository()

    val character: MutableLiveData<Character> = MutableLiveData()

    fun loadCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)

            val r = repo.loadById(id)
            if (r.code() == 200)
                character.postValue(r.body()?.data?.results?.get(0))
            else
                toast.postValue(r.message())

            loading.postValue(false)
        }
    }
}