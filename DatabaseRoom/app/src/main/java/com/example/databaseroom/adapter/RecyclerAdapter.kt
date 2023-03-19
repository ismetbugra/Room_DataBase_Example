package com.example.databaseroom.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.R
import com.example.databaseroom.data.User
import com.example.databaseroom.data.UserViewModel
import com.example.databaseroom.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*
import kotlin.coroutines.coroutineContext


class RecyclerAdapter(var mUserViewModel: UserViewModel): RecyclerView.Adapter<RecyclerAdapter.MyHolder>() {
    private var userList= emptyList<User>()


    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentItem=userList[position]
        holder.itemView.firstNameText.text=currentItem.firstName
        holder.itemView.lastNameText.text=currentItem.lastName
        holder.itemView.ageText.text=currentItem.age.toString()
        holder.itemView.idText.text=currentItem.id.toString()

        holder.itemView.rowId.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


        // delete ıconuna tıklandıgında o kullanıcıyı silme işlemi ---- !! bir görüntüden her zaman context alınabılır
        holder.itemView.deleteImage.setOnClickListener {
            val builder=AlertDialog.Builder(holder.itemView.context)
            builder.setPositiveButton("Yes"){_,_->
                deleteUser(currentItem)
                Toast.makeText(holder.itemView.context,"User deleted",Toast.LENGTH_LONG)
            }
            builder.setNegativeButton("No"){_,_->}
            builder.setTitle("Delete ${currentItem.firstName}")
            builder.setMessage("Are you want to delete ${currentItem.firstName}${currentItem.lastName}")
            builder.create().show()
        }

    }

    // listeyi guncellıyoruz
    fun setData(userList:List<User>){
        this.userList=userList
        notifyDataSetChanged()
    }

    fun deleteUser(user: User){
        mUserViewModel.deleteUser(user)

    }


}


