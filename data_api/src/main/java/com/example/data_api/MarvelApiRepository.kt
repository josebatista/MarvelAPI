package com.example.data_api

import com.example.data.Repository
import com.example.data.model.Response
import com.example.data_api.api.MarvelApi

class MarvelApiRepository : Repository {

    companion object {
        private const val OFFSET_VALUE = 20
    }

    override fun loadAll(page: Int): retrofit2.Response<Response> {
        return MarvelApi.getService().load((page * OFFSET_VALUE)).execute()
    }

    override fun loadById(id: Int): retrofit2.Response<Response> {
        return MarvelApi.getService().loadCharacter(id).execute()
    }
}
