package com.cazimir.skeletonsingleactivitymvvm.dialog.base

import androidx.fragment.app.DialogFragment

//this class makes sure that when rotating the device, the dialogs remain there and do not recreate
open class RetainableDialogFragment() : DialogFragment() {

    init {
        retainInstance = true
    }

    override fun onDestroyView() {

        val dialog = dialog
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }
}