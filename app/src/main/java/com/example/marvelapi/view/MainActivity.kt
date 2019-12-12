package com.example.marvelapi.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.marvelapi.R
import com.example.marvelapi.view.base.BaseActivity
import com.example.presentation.CharacterListViewModel

class MainActivity : BaseActivity() {

    private val viewmodel: CharacterListViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewmodel.loading.observe(this, loadingStateObserver)

        viewmodel.loadCharacters()
    }
}
