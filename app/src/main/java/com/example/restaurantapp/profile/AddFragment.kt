package com.example.restaurantapp.profile

import android.Manifest
import android.app.Activity
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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.model.User
import com.example.restaurantapp.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.coroutines.launch


class AddFragment : Fragment() {

    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var photoMap: Bitmap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//

        view.choose_photo.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 26){
                if(context?.let { it1 -> ContextCompat.checkSelfPermission(it1, Manifest.permission.READ_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
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

        view.add_button.setOnClickListener {
            insertDataToDatabase()

        }

        return view
    }


    private fun  insertDataToDatabase(){
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text
        val photoLink = photo_link.text.toString()

        if(inputCheck(firstName, lastName, age, photoLink)){
            //create user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()), photoLink)

            //add data to database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

            //navigating back
            findNavController().navigate(R.id.action_addFragment_to_userProfile)
        }else{
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable, photoLink: String): Boolean {
        //return false if empty
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(photoLink))
    }


    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle the permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
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
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            photo_link.text = data?.data.toString()
        }
    }


    //using coil to convert uri
//    private suspend fun getBitmap(uri: Uri): Bitmap {
//        val loading = context?.let{it1 -> ImageLoader(it1)}
//        val request = context?.let{it1 -> ImageRequest.Builder(it1).data(uri).build()}
//
//        val result: Drawable = (request?.let { loading?.execute(it) } as SuccessResult).drawable
//        return (result as BitmapDrawable).bitmap
//    }
}