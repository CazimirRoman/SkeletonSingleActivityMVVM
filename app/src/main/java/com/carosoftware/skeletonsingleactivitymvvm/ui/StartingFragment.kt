package com.carosoftware.skeletonsingleactivitymvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carosoftware.skeletonsingleactivitymvvm.databinding.StartingFragmentBinding

class StartingFragment : Fragment() {

    private lateinit var binding: StartingFragmentBinding

    companion object {
        fun newInstance() = StartingFragment()
    }

    private lateinit var viewModel: StartingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(StartingViewModel::class.java)
    }
}