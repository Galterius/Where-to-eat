package com.example.restaurantapp.profile.update

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.restaurantapp.R
import com.example.restaurantapp.model.User
import com.example.restaurantapp.profile.AddFragment
import com.example.restaurantapp.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {

    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    private lateinit var photoMap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())
        view.update_Email_addr.setText(args.currentUser.email)
        view.update_phone_num.setText(args.currentUser.phoneNumber)
        view.update_photo_link.text = args.currentUser.profilePhoto



        view.update_photo.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 26){
                if(context?.let { it1 -> ContextCompat.checkSelfPermission(it1, Manifest.permission.READ_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, UpdateFragment.PERMISSION_CODE)
                }
                else{
                    pickImageFromGallery()
                }
            }
            else
            {
                pickImageFromGallery()
            }
        }

        view.update_button.setOnClickListener {
            updateItem()
        }


        //Add a menu
        setHasOptionsMenu(true)

        return view
    }


    private  fun updateItem(){
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())
        val email = update_Email_addr.text.toString()
        val phone = update_phone_num.text.toString()
        val photoLink = update_photo_link.text.toString()

        if(inputCheck(firstName, lastName, updateAge_et.text, email, phone ,photoLink)){
            //creating the updated user object
            val updateUser = User(args.currentUser.id, firstName, lastName, age,email, phone ,photoLink)

            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()

            //navigating back to users
            findNavController().navigate(R.id.action_updateFragment_to_userProfile)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable,email: String, phone: String ,photoLink: String): Boolean {
        //return false if empty
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(photoLink) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }


    //asking the user if he wants to delete the item
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Deleted: ${args.currentUser.firstName}", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_userProfile)
        }

        builder.setNegativeButton("No"){_, _ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Do you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }


    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, UpdateFragment.IMAGE_PICK_CODE)
    }

    //handle the permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            UpdateFragment.PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission granted
                    pickImageFromGallery()
                }else{
                    //permission denied
                    Log.i("permission","permission denied")
                }
            }
        }
    }

    //handle the result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == UpdateFragment.IMAGE_PICK_CODE){
                update_photo_link.text= data?.data.toString()
        }
    }
//
//
//    //using coil to convert uri
//    private suspend fun getBitmap(uri: Uri): Bitmap {
//        val loading = context?.let{it1 -> ImageLoader(it1) }
//        val request = context?.let{it1 -> ImageRequest.Builder(it1).data(uri).build()}
//
//        val result: Drawable = (request?.let { loading?.execute(it) } as SuccessResult).drawable
//        return (result as BitmapDrawable).bitmap
//    }

}