package com.example.data_api

import android.util.Log
import com.example.data.Repository
import com.example.data_api.api.MarvelApi

class MarvelApiRepository : Repository {

    override fun loadAll() {
        val r = MarvelApi.getService().load().execute()
        Log.d("TAG", r.body()?.data!!.results[3].name)
    }

    override fun loadById(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
