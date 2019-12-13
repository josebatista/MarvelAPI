package com.example.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkState {

    companion object {
        fun hasConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

            return activeNetwork?.isConnectedOrConnecting == true
        }
    }

}