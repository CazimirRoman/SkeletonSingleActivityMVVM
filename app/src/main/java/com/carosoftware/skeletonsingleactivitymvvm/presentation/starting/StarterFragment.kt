package com.carosoftware.skeletonsingleactivitymvvm.presentation.starting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carosoftware.skeletonsingleactivitymvvm.databinding.StartingFragmentBinding
import timber.log.Timber

class StarterFragment : Fragment() {

    private var startingAdapter: StartingAdapter = StartingAdapter(mutableListOf())

    // use view binding instead of synthetics
    private lateinit var binding: StartingFragmentBinding

    companion object {
        fun newInstance() = StarterFragment()
    }

    private lateinit var viewModel: StarterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(StarterViewModel::class.java)

        binding.starterList.apply {
            adapter = startingAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getStarters()

        viewModel.starters.observe(viewLifecycleOwner, Observer {
            Timber.d("Starters observe called in Fragment")
            startingAdapter.setUpData(it)
        })
    }
}