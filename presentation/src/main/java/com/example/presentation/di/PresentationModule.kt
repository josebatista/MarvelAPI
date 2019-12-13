package com.example.presentation.di

import com.example.data.Repository
import com.example.data_api.MarvelApiRepository
import com.example.presentation.viewmodel.CharacterDetailViewModel
import com.example.presentation.viewmodel.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single {
        MarvelApiRepository() as Repository
    }

    viewModel {
        CharacterListViewModel(repo = get())
    }

    viewModel {
        CharacterDetailViewModel(repo = get())
    }
}