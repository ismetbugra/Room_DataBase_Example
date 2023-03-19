package com.example.databaseroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.R
import com.example.databaseroom.adapter.RecyclerAdapter
import com.example.databaseroom.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*

//import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        recyclerAdapter= RecyclerAdapter(mUserViewModel)
        recyclerView.adapter=recyclerAdapter
        context?.let {
            recyclerView.layoutManager=LinearLayoutManager(it)
        }

        // UserViewModel olusturuldu ve degısıklık varsa observe data fonkunda degısıklıkler recyclerviewa uygulandı

        observeData()

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }

    fun observeData(){
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            recyclerAdapter.setData(user)
        })
    }


}