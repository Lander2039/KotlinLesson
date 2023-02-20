package com.example.kotlinlesson.presentation.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinlesson.App
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.ActivityMainBinding
import com.example.kotlinlesson.utils.permission.PermissionUtils
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var navController: NavController

    private lateinit var navHostFragment: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        (applicationContext as App).provideAppComponent().inject(this)

//        binding.btnOpenSecondActivity.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
//            Animatoo.animateCard(this)
//        }

        viewModel.checkUserExists()

        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment // Инициализация

        navController = navHostFragment.navController // Создаем нав контроллеер

        viewModel.nav.observe(this) {
            navController.graph = getNavGraph()
//            navController.setGraph(it)
        }

        navController.addOnDestinationChangedListener(this)

        binding.bottonNavigation.setupWithNavController(navController)

        viewModel.visibility.observe(this) {
            binding.bottonNavigation.visibility = it
        }
    }

    private fun getNavGraph(): NavGraph {
        val navGraph = navHostFragment.navController.navInflater.inflate(
            R.navigation.auth_graph
        )
        val random = (1..2).random()
        if (random == 1) {
            navGraph.startDestination = R.id.loginFragment
        } else {
            navGraph.startDestination = R.id.homeFragment
        }
        return navGraph
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        viewModel.destinationChanged(destination)
    }

    override fun onStart() {
        super.onStart()
        when {
            PermissionUtils.checkAccessFineLocationGranted(this) -> {
                when {
                    PermissionUtils.isLocationEnabled(this) -> {
                        setLocationListner()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(this)
                    }
                }
            }
            else -> {
                PermissionUtils.askAccessFineLocationPermission(
                    this,
                    42
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            42 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(this) -> {
                            setLocationListner()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(this)
                        }
                    }
                } else {
                    Toast.makeText(this, "not granted permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setLocationListner() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        Log.w("location", "${location.latitude} ${location.longitude}")
                    }
                }
            },
            Looper.myLooper()
        )
    }



    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }
}
