package be.bf.pawso.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.bf.pawso.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}