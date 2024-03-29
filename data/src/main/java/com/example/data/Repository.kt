package com.example.data

import com.example.data.model.Response

interface Repository {

    fun loadAll(page: Int): retrofit2.Response<Response>
    fun loadById(id: Int): retrofit2.Response<Response>

}