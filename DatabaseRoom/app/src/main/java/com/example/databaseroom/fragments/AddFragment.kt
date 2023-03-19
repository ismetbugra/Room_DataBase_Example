package com.example.databaseroom.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.databaseroom.R
import com.example.databaseroom.data.User
import com.example.databaseroom.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    fun insertDataToDatabase(){
        val firstName=etFirstName.text.toString()
        val lastName=etLastName.text.toString()
        val age = etAge.text

        // veriler girildi mi null mı kontrolu
        if(inputCheck(firstName,lastName,age)){
            //Creating user
            val user= User(0,firstName,lastName,Integer.parseInt(age.toString()))
            // add data to database
            mUserViewModel.addUser(user)
            context?.let {
                Toast.makeText(it,"Added",Toast.LENGTH_LONG).show()
            }
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }
    }

    // veriler girildi mi null mı kontrolu
    fun inputCheck(firstName:String, lastName:String, age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&&age.isEmpty())
    }


}