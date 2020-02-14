package com.gabcode.search_meli.ui.util

import android.content.Context
import android.os.ResultReceiver
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtil {

    fun hide(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun show(view: View) { //plaid
        val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // the public methods don't seem to work for me, so… reflection.
        try {
            val showSoftInputUnchecked = InputMethodManager::class.java.getMethod(
                "showSoftInputUnchecked", Int::class.javaPrimitiveType, ResultReceiver::class.java
            )
            showSoftInputUnchecked.isAccessible = true
            showSoftInputUnchecked.invoke(inputMethodManager, 0, null)
        } catch (e: Exception) { }
    }
}
