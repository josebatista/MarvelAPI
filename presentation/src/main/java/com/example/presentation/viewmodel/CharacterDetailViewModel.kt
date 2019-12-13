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

class CharacterDetailViewModel(
    private val repo: Repository,
    private val context: Context
) : BaseViewModel() {

    val character: MutableLiveData<Character> = MutableLiveData()

    fun loadCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loading.postValue(true)
                if (NetworkState.hasConnection(context)) {
                    val r = repo.loadById(id)
                    if (r.code() == 200)
                        character.postValue(r.body()?.data?.results?.get(0))
                    else
                        toast.postValue(r.message())
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