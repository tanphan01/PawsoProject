package be.bf.pawso.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import be.bf.pawso.R
import be.bf.pawso.databinding.ActivityMessageBinding
import be.bf.pawso.models.CatWithShelter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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
        binding.tvShelterCatMessageActivity.text = cat.shelter.nameShelter
        binding.tvMoodCatMessageActivity.text = cat.cat.adjective

        Glide.with(this)
            .load(cat.cat.image)
            .centerCrop()
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imgCatMessageActivity)

    }

    fun call(view: View) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + "+32494521431")
        startActivity(dialIntent)
    }
}