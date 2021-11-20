package com.vaasugambhir.furniturekart.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import com.vaasugambhir.furniturekart.R

class ConfirmDialog(private val activity: Activity) {
    private lateinit var dialog: AlertDialog


    fun startLoading() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.confirm_layout, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}