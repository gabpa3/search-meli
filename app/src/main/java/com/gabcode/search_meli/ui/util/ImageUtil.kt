package com.gabcode.search_meli.ui.util

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.gabcode.search_meli.ui.extension.imageLoader

object ImageUtil {

    private val TAG = ImageUtil.javaClass.simpleName.toString()

    fun loadUrl(imageView: ImageView, url: String) {
        imageView.load(url, imageLoader = (imageView.context as AppCompatActivity).imageLoader)
    }

    fun loadFull(imageView: ImageView, url: String) {
        imageView.load(url, imageLoader = (imageView.context as AppCompatActivity).imageLoader){
            listener(
                onError = { _ , throwable ->
                    Log.e(TAG, "LoadError", throwable)
                }
            )
        }
    }
}
