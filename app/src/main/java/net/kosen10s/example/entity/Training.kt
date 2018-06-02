package net.kosen10s.example.entity

import java.time.format.ResolverStyle

data class Training constructor(
        var name: String,
        var description: String,
        var category: String,
        var image: Int,
        var targetNum: Int
)