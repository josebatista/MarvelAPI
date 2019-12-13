package com.example.marvelapi.view

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapi.R
import com.example.marvelapi.adapter.CharacterListAdapter
import com.example.marvelapi.view.base.BaseActivity
import com.example.presentation.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewmodel: CharacterListViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
    }

    private lateinit var adapter: CharacterListAdapter

    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel.loading.observe(this, loadingStateObserver)
        viewmodel.toast.observe(this, toastObserver)
        viewmodel.characterList.observe(this, Observer {
            if (recyclerState != null) {
                rv_characters.layoutManager?.onRestoreInstanceState(recyclerState)
            }
            adapter.addCharacters(it)
        })

        init()

        viewmodel.loadCharacters()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("asd", rv_characters.layoutManager?.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        recyclerState = savedInstanceState.getParcelable("asd")
    }

    private fun init() {
        adapter = CharacterListAdapter(::onClick)
        rv_characters.adapter = adapter
        rv_characters.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun onClick(id: Int) {
        DetailsActivity.open(this, id)
    }
}
