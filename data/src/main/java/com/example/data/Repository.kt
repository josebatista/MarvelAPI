package com.example.data

import com.example.data.model.Response

interface Repository {

    fun loadAll() : Response?
    fun loadById(id: Int)

}