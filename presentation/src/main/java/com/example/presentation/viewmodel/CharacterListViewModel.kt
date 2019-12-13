package com.example.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.model.Character
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repo: Repository
) : BaseViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    var actualPage = -1
        private set

    fun loadCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)

            if (page > actualPage) {
                actualPage = page
                val r = repo.loadAll(page)
                if (r.code() == 200)
                    characterList.postValue(r.body()?.data?.results)
                else
                    toast.postValue(r.message())
            }

            loading.postValue(false)
        }
    }
}
