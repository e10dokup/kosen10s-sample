package net.kosen10s.example.entity

import se.ansman.kotshi.JsonSerializable

@JsonSerializable data class Training constructor(
        var name: String,
        var category: String,
        var image: String,
        var description: String,
        var target: String
)