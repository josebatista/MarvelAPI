package com.example.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.model.Character
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.util.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repo: Repository,
    private val context: Context
) : BaseViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    var actualPage = -1
        private set

    fun loadCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loading.postValue(true)

                if (NetworkState.hasConnection(context)) {
                    if (page > actualPage) {
                        actualPage = page
                        val r = repo.loadAll(page)
                        if (r.code() == 200)
                            characterList.postValue(r.body()?.data?.results)
                        else
                            toast.postValue(r.message())
                    }
                } else {
                    toast.postValue(context.getString(R.string.verify_internet))
                }

            } catch (e: Exception) {
                toast.postValue(e.message)
            } finally {
                loading.postValue(false)
            }
        }
    }
}
