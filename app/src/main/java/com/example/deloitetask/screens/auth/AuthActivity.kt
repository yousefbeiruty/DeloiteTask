package com.example.deloitetask.screens.auth

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.deloitetask.R
import com.example.deloitetask.common.BaseBindingActivity
import com.example.deloitetask.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseBindingActivity<ActivityAuthBinding>(R.layout.activity_auth) {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_fragment_pre_login)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment, R.id.signUpFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        onDestinationChanged()
        onTabSelected()
        onBackPressedScreen()
    }

    private fun onTabSelected() {
        // Set up TabLayout click listener to navigate to destinations
        viewBinding?.tabLoginOrSignUpLayout?.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val destinationId = getDestinationForTab(tab.position)
                navController.navigate(destinationId)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun onDestinationChanged() {
        // Observe destination changes and update selected tab
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val tab = getTabForDestination(destination.id)
            viewBinding?.tabLoginOrSignUpLayout?.selectTab(tab)

        }
    }

    private fun onBackPressedScreen() {
        // Set up OnBackPressedCallback
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentDestination = navController.currentDestination
                val desiredDestination = navController.graph.findNode(R.id.loginFragment)

                if (currentDestination == desiredDestination) {
                    // Finish the activity
                    finish()
                } else {
                    // Handle regular back navigation
                    navController.navigateUp()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun getTabForDestination(destinationId: Int): TabLayout.Tab? {
        for (i in 0 until viewBinding?.tabLoginOrSignUpLayout?.tabCount !!) {
            val tab = viewBinding?.tabLoginOrSignUpLayout?.getTabAt(i)
            val destination = getDestinationForTab(i)
            if (destination == destinationId) {
                return tab
            }
        }
        return null
    }

    private fun getDestinationForTab(position: Int): Int {
        return when (position) {
            0 -> {
                R.id.loginFragment
            }
            1 -> {
                R.id.signUpFragment
            }
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}