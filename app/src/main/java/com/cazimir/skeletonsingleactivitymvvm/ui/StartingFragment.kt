package com.cazimir.skeletonsingleactivitymvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cazimir.skeletonsingleactivitymvvm.R

class StartingFragment : Fragment() {

    companion object {
        fun newInstance() = StartingFragment()
    }

    private lateinit var viewModel: StartingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.starting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(StartingViewModel::class.java)
    }
}