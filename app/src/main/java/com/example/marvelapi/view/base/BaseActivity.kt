package com.example.marvelapi.view.base

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.marvelapi.R

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: Dialog? = null

    private fun showLoading() {
        if (loadingDialog == null) {
            initDialog()
        }
        loadingDialog?.show()
    }

    private fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun initDialog() {
        loadingDialog = Dialog(this)
        loadingDialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(R.layout.dialog_loading)
        }
    }

    protected var loadingStateObserver = Observer<Boolean> { loading ->
        if (loading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

}