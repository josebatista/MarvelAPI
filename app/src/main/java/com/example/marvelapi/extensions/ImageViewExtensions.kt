package com.example.marvelapi.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String) {
    Picasso.get().load(url).into(this)
}