package net.kosen10s.example.entity

import se.ansman.kotshi.JsonSerializable

@JsonSerializable data class TopHeadlines(
        val status: String,
        val totalResults: Int,
        val articles: List<Article>
)