package net.kosen10s.example.entity

import se.ansman.kotshi.JsonSerializable

@JsonSerializable data class Article(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String
)