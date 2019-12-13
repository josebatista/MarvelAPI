package com.example.data_api

import com.example.data.Repository
import com.example.data.model.Response
import com.example.data_api.api.MarvelApi

class MarvelApiRepository : Repository {

    override fun loadAll(): retrofit2.Response<Response> {
        return MarvelApi.getService().load().execute()
    }

    override fun loadById(id: Int): retrofit2.Response<Response> {
        return MarvelApi.getService().loadCharacter(id).execute()
    }
}
