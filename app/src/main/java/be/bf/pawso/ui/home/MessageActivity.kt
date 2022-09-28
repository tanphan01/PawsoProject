package be.bf.pawso.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.bf.pawso.databinding.ActivityMessageBinding
import be.bf.pawso.models.CatWithShelter

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cat = intent.getSerializableExtra("cat") as CatWithShelter

        binding.tvNameCatMessageActivity.text = cat.cat.name
        binding.tvGenderCatMessageActivity.text = cat.cat.gender
        binding.tvAgeCatMessageActivity.text = cat.cat.age
        binding.tvShelterCatMessageActivity.text = cat.cat.shelterId
        binding.tvMoodCatMessageActivity.text = cat.cat.adjective

    }
}