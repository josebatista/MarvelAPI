package com.example.marvelapi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.marvelapi.R
import com.example.marvelapi.extensions.load
import com.example.marvelapi.view.base.BaseActivity
import com.example.presentation.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    private val viewmodel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent?.extras?.getInt(ID)

        viewmodel.loading.observe(this, loadingStateObserver)
        viewmodel.toast.observe(this, toastObserver)

        viewmodel.character.observe(this, Observer {
            iv_character_detail.load("${it.thumbnail.path}/landscape_medium.${it.thumbnail.extension}")
            tv_detail_character_name.text = it.name
            tv_detail_character_desc.text = it.description

            initTitlebar(it.name)
        })

        viewmodel.loadCharacter(id!!)

    }

    private fun initTitlebar(title: String) {
        setSupportActionBar(toolbar)

        if (appbar != null) {
            if (appbar.layoutParams is CoordinatorLayout.LayoutParams) {
                val lp = appbar.layoutParams as CoordinatorLayout.LayoutParams
                lp.height = resources.displayMetrics.widthPixels
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (collapsetoolbar != null) {
            supportActionBar?.setDisplayShowTitleEnabled(true)
            collapsetoolbar.title = title
        } else {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    companion object {

        private const val ID = "character_id"

        fun open(context: Context, id: Int) {
            context.startActivity(
                Intent(context, DetailsActivity::class.java).apply {
                    putExtra(ID, id)
                }
            )
        }
    }

}
