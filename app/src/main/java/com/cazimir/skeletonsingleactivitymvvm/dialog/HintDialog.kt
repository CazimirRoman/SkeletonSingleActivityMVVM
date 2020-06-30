package com.cazimir.skeletonsingleactivitymvvm.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cazimir.skeletonsingleactivitymvvm.R
import com.cazimir.skeletonsingleactivitymvvm.dialog.base.RetainableDialogFragment
import com.cazimir.skeletonsingleactivitymvvm.eventbus.EventBusShowHintsEvent
import com.cazimir.skeletonsingleactivitymvvm.model.HintType
import kotlinx.android.synthetic.main.hint_dialog.view.*
import org.greenrobot.eventbus.EventBus

class HintDialog(val hintText: String, val hintType: HintType) :
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