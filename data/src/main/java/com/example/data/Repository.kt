package com.example.data

interface Repository {

    fun loadAll()
    fun loadById(id: Int)

}