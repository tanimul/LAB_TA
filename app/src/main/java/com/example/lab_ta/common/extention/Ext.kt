package com.example.lab_ta.common.extention

import android.app.Activity
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.lab_ta.R
import com.google.android.material.snackbar.Snackbar

fun String.checkIsEmpty(): Boolean =
    isNullOrEmpty() || "" == this || this.equals("null", ignoreCase = true)

fun Activity.snackBar(msg: String, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(findViewById(android.R.id.content), msg, length).setTextColor(Color.WHITE).show()

fun Activity.snackBarError(msg: String) {
    val snackBar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
    val sbView = snackBar.view
    sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
    snackBar.setTextColor(Color.WHITE)
    snackBar.show()
}