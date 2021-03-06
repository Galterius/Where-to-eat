package com.example.restaurantapp.profile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapp.R
import com.example.restaurantapp.detail.DetailViewModel
import com.example.restaurantapp.userviewmodel.FavoriteViewModel
import com.example.restaurantapp.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.costum_row.view.*
import kotlinx.android.synthetic.main.login_fragment.view.*

class UserProfileFragment: Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private val mFavoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        //recyclerview
        val adapter = ListAdapter()

        val recyclerView = view.recyclerview


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        val mFavoriteViewModel: FavoriteViewModel by lazy {
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        }

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
            mFavoriteViewModel.readAllData.observe(viewLifecycleOwner, Observer { favorite ->
                adapter.setFavorite(favorite)
                mFavoriteViewModel.deleteFavorite(adapter.duplicatedRestaurant)
            })
        })


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_userProfile_to_addFragment2)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }

        return super.onOptionsItemSelected(item)
    }



    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mUserViewModel.deleteAllUser()
            mFavoriteViewModel.deleteAllFavorite()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_LONG).show()

        }

        builder.setNegativeButton("No"){_, _ -> }

        builder.setTitle("Delete everything?")
        builder.setMessage("Do you want to delete every user?")
        builder.create().show()
    }

}