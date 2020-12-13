package com.example.restaurantapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapp.R
import com.example.restaurantapp.userviewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.costum_row.view.*
import kotlinx.android.synthetic.main.login_fragment.view.*

//class UserFavorite: Fragment() {
//    private lateinit var mFavoriteViewModel: FavoriteViewModel
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.costum_row, container, false)
//        val adapter = ListAdapterFavorite()
//        val recyclerView = view.recyclerview
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
//        mFavoriteViewModel.readAllData.observe(viewLifecycleOwner, Observer { restaurant ->
//            adapter.setFavorite(restaurant)
//        })
//
//        return view
//    }
//}