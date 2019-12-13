package com.example.data_api

import com.example.data.Repository
import com.example.data.model.Response
import com.example.data_api.api.MarvelService

class MarvelApiRepository : Repository {

    private val api by lazy {
        MarvelService.getService()
    }

    companion object {
        private const val OFFSET_VALUE = 20
    }

    override fun loadAll(page: Int): retrofit2.Response<Response> {
        return api.load((page * OFFSET_VALUE)).execute()
    }

    override fun loadById(id: Int): retrofit2.Response<Response> {
        return api.loadCharacter(id).execute()
    }
}
