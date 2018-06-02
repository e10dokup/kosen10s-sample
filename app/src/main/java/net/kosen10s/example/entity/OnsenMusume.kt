package net.kosen10s.example.entity

import se.ansman.kotshi.JsonSerializable

/**
 * Created by arayaryoma on 6/2/18.
 */

data class OnsenMusume(
        val id: String,
        val name: String,
        val imageUri: String,
        val speechText: String
)

@JsonSerializable data class OnsenMusumeData(
        val id: String,
        val name: String,
        val image_name: String,
        val speech_texts: Array<SpeechText>
)

@JsonSerializable data class SpeechText(
        val point: Int,
        val text: String
)
