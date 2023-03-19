package com.example.databaseroom

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.databaseroom.data.UserViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.delete_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)

    }

    fun deleteAllUsers(){
        val builder= AlertDialog.Builder(this)
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(this,"User deleted", Toast.LENGTH_LONG)
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Deleted All Users?")
        builder.setMessage("Are you want to delete all")
        builder.create().show()
    }



}