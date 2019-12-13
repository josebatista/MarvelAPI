package com.example.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.model.Character
import com.example.data_api.MarvelApiRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel : BaseViewModel() {

    private val repo: Repository = MarvelApiRepository()

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            if (loading.value == null) {
                loading.postValue(true)

                val r = repo.loadAll()
                if (r.code() == 200)
                    characterList.postValue(r.body()?.data?.results)
                else
                    toast.postValue(r.message())

                loading.postValue(false)
            }
        }
    }
}