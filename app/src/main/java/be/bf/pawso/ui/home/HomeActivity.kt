package be.bf.pawso.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import be.bf.pawso.R
import be.bf.pawso.databinding.ActivityHomeBinding
import be.bf.pawso.models.User
import be.bf.pawso.ui.home.viewmodel.CatViewModel
import be.bf.pawso.ui.home.viewmodel.CatViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private val viewModel : CatViewModel by viewModels { CatViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra("user") as User?

        if (user != null) {
            viewModel.setUser(user)
        }

        val navController = findNavController(R.id.home_nav_host_fragment)
        binding.bottomNav.setupWithNavController(navController)

    }
}