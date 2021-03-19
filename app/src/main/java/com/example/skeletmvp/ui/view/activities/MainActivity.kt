package com.example.skeletmvp.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding
import com.example.skeletmvp.ui.view.fragments.coordinator.CoordinatorFragment

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarConfig = AppBarConfiguration.Builder(
            R.id.authFragment,
            R.id.coordinatorFragment
        ).build()

        navController = Navigation.findNavController(this,R.id.fragment_container)
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig)
    }

    override fun onSupportNavigateUp()= navController.navigateUp()

}