package com.example.restaurantapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.databinding.FragmentDetailBinding
import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.userviewmodel.FavoriteViewModel

class DetailFragment: Fragment() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.lifecycleOwner = this

        val restaurant = DetailFragmentArgs.fromBundle(requireArguments()).selectedRestaurant

        val viewModelFactory = DetailViewModelFactory(restaurant, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.clicked.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModel.restaurantLocation.observe(viewLifecycleOwner, Observer {
                    if(viewModel.clicked.value == true) {
                        startActivity(it)
                        viewModel.navigatedToMap()
                    }
                })
            }
        })

        viewModel.insertIntoDatabase.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.name.observe(viewLifecycleOwner, Observer { rest_name ->
                    val newFavRestaurant = Restaurant(0, rest_name)
                    mFavoriteViewModel.addFavorite(newFavRestaurant)
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
                    viewModel.insertedToDb()
                })
            }
        })

        return binding.root
    }
}