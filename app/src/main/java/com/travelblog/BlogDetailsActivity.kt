package com.travelblog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.travelblog.databinding.ActivityBlogDetailsBinding

class BlogDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageMain.setImageResource(R.drawable.rio_image)
        binding.imageAvatar.setImageResource(R.drawable.avatar)

        binding.textTitle.text = "O paraíso chamado Rio de Janeiro "
        binding.textDate.text = "12 de Setembro, 2022"
        binding.textAuthor.text = "Fausto Silva"
        binding.textRating.text = "4.4"
        binding.textViews.text = "(2687 views)"
        binding.textDescription.text = "O Rio de Janeiro é um lugar maravilhoso com praias exuberantes! "

        binding.ratingBar.rating = 4.4f

        binding.imageBack.setOnClickListener { finish() }
    }
}