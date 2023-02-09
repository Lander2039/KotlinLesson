package com.example.kotlinlesson.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
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

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }
}