package com.carosoftware.skeletonsingleactivitymvvm.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carosoftware.skeletonsingleactivitymvvm.R
import com.carosoftware.skeletonsingleactivitymvvm.presentation.dialog.base.RetainableDialogFragment
import com.carosoftware.skeletonsingleactivitymvvm.eventbus.EventBusShowHintsEvent
import kotlinx.android.synthetic.main.hint_dialog.view.*
import org.greenrobot.eventbus.EventBus

class HintDialog(val hintText: String, val hintType: com.carosoftware.skeletonsingleactivitymvvm.domain.HintType) :
    RetainableDialogFragment() {

    private lateinit var layout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = activity!!.layoutInflater.inflate(R.layout.hint_dialog, null)

        layout.hint_text.text = hintText

        layout.ok_button.setOnClickListener {
            dialog?.dismiss()
        }

        layout.never_show_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                EventBus.getDefault().post(EventBusShowHintsEvent(hintType, !isChecked))
            }
        }

        return layout
    }
}