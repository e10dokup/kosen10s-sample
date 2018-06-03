package net.kosen10s.example.entity

/**
 * Created by allajah on 6/3/18.
 */
data class MatchingPartner(
        val id: String,
        val name: String,
        val imageName: String,
        val necessaryPoints: Int,
        val favoriteMuscleImageName: String
)

data class MatchingPartnerData(
        val id: String,
        val name: String,
        val image_name: String,
        val necessary_points: Int,
        val favorite_muscle_image_name: String
)
