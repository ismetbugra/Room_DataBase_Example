package com.example.databaseroom.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.databaseroom.R
import com.example.databaseroom.data.User
import com.example.databaseroom.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel
    //private lateinit var currentUserUpdate:User

    //eski argument yerine bu yapıyı kullan
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)


            tvUpdateFirstname.setText(args.currentUser.firstName)
            tvUpdateLastname.setText(args.currentUser.lastName)
            tvUpdateAge.setText(args.currentUser.age.toString())


        UpdateButton.setOnClickListener {

            updateItem()
        }


    }

   fun updateItem(){
        val firstName=tvUpdateFirstname.text.toString()
        val lastName=tvUpdateLastname.text.toString()
        val age=tvUpdateAge.text

        if (inputCheck(firstName,lastName,age)){

            val updatedUser=User(args.currentUser.id,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"User updated",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please enter users informations kindly",Toast.LENGTH_LONG).show()
        }


    }

    // veriler girildi mi null mı kontrolu
    fun inputCheck(firstName:String, lastName:String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&&age.isEmpty())
    }

}