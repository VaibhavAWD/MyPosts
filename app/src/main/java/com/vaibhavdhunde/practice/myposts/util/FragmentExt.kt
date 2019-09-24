@file:Suppress("DEPRECATION")

package com.vaibhavdhunde.practice.myposts.util

import android.app.ProgressDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

private lateinit var progressDialog: ProgressDialog

fun Fragment.showProgress(message: String) {
    progressDialog = indeterminateProgressDialog(message)
}

fun Fragment.showProgress(message: Int) {
    progressDialog = indeterminateProgressDialog(message)
}

fun hideProgress() {
    if (::progressDialog.isInitialized && progressDialog.isShowing) {
        progressDialog.dismiss()
    }
}

fun Fragment.closeSoftKeyboard() {
    val view = activity?.currentFocus
    view?.let {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}