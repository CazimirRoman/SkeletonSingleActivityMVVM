package com.carosoftware.skeletonsingleactivitymvvm.presentation.dialog.examples

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.carosoftware.skeletonsingleactivitymvvm.R
import com.carosoftware.skeletonsingleactivitymvvm.presentation.dialog.base.RetainableDialogFragment
import com.carosoftware.skeletonsingleactivitymvvm.presentation.dialog.callback.ISimpleOkDialogCallback

class ExampleSimpleOkDialog(
    private val callback: ISimpleOkDialogCallback,
    private val title: String,
    private val message: String
) : RetainableDialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        builder.setPositiveButton(getString(R.string.ok), this)
        builder.setMessage(message)
        builder.setIcon(R.mipmap.ic_launcher)
        return builder.setTitle(title).create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        callback.okClicked()
        dialog.dismiss()
    }
}
