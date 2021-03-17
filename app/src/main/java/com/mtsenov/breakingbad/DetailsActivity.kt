package com.mtsenov.breakingbad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mtsenov.breakingbad.databinding.ActivityDetailsBinding
import com.mtsenov.breakingbad.model.SeriesChar


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var characterDetails: SeriesChar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        if (intent != null && intent.hasExtra("character")) {
            characterDetails = intent.getParcelableExtra("character")!!
        }

        //Load image
        Glide.with(this)
            .load(characterDetails.img)
            .error(Glide.with(binding.imageView).load(R.drawable.ic_launcher_background))
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imageView)

        binding.tvAppearance.text = characterDetails.appearanceList.joinToString()
        binding.tvName.text = characterDetails.name
        binding.tvNickname.text = characterDetails.nickname
        binding.tvOccupation.text = characterDetails.occupationList.joinToString(
            separator = "\n"
        )
        binding.tvStatus.text = characterDetails.status
        setContentView(view)
    }
}
