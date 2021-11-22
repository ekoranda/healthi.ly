package com.cs506.healthily.utility

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.cs506.healthily.R
import com.cs506.healthily.databinding.DialogLayoutBinding

//Initiate a loding animation
class LoadingDialog(private val activity: Activity) {

    private var alertDialog: AlertDialog? = null

    fun startLoading() {
        val builder = AlertDialog.Builder(activity, R.style.loadingDialogStyle)
        val binding = DialogLayoutBinding.inflate(LayoutInflater.from(activity), null, false)

        builder.setView(binding.root)
        builder.setCancelable(false)
        alertDialog = builder.create()
        binding.rotateLoading.start()
        alertDialog?.show()
    }

    fun stopLoading() {
        alertDialog?.dismiss()
    }
}