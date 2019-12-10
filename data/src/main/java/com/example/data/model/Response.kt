package com.example.data.model

data class Response(
    val code: Int,
    val status: String,
    val etag: String,
    val data: Data
)