package com.carosoftware.skeletonsingleactivitymvvm.dialog.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carosoftware.skeletonsingleactivitymvvm.R
import com.carosoftware.skeletonsingleactivitymvvm.dialog.base.RetainableDialogFragment
import com.carosoftware.skeletonsingleactivitymvvm.ui.about.AboutFragment

class ExampleDialogWithCustomLayout(val callback: IExampleCallbackInterface) :
    RetainableDialogFragment() {

    private lateinit var layout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = activity!!.layoutInflater.inflate(R.layout.example_dialog_with_custom_layout, null)
        childFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, AboutFragment())
            .commit()
        return layout
    }
}