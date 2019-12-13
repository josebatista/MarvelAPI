package com.example.marvelapi.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.R
import com.example.marvelapi.adapter.CharacterListAdapter
import com.example.marvelapi.view.base.BaseActivity
import com.example.presentation.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewmodel: CharacterListViewModel by viewModel()

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

        viewmodel.loadCharacters(0)
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
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_characters.layoutManager = llm
        rv_characters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition = llm.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == adapter.itemCount - 1 && !viewmodel.loading.value!!) {
                    viewmodel.loadCharacters(viewmodel.actualPage + 1)
                }
            }
        })
    }

    private fun onClick(id: Int) {
        Log.d("TAG", "clicouuuuuuu $id")
        DetailsActivity.open(this, id)
    }
}
