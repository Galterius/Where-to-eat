package com.example.restaurantapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val restaurant = DetailFragmentArgs.fromBundle(requireArguments()).selectedRestaurant

        val viewModelFactory = DetailViewModelFactory(restaurant, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.clicked.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModel.restaurantLocation.observe(viewLifecycleOwner, Observer {
                    if(viewModel.clicked.value == true) {
                        startActivity(it)
                    }
                })
            }
            viewModel.navigatedToMap()
        })


        return binding.root
    }
}