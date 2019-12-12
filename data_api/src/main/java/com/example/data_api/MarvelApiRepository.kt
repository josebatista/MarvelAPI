package com.example.data_api

import com.example.data.Repository
import com.example.data.model.Response
import com.example.data_api.api.MarvelApi

class MarvelApiRepository : Repository {

    override fun loadAll(): Response? {
        val r = MarvelApi.getService().load().execute()

        return r.body()
    }

    override fun loadById(id: Int): Response? {
        val r = MarvelApi.getService().loadCharacter(id).execute()

        return r.body()
    }
}
