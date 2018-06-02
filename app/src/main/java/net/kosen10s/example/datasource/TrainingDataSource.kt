package net.kosen10s.example.datasource

import net.kosen10s.example.entity.Training

class TrainingDataSource {

    private var trainings : Array<Training>? = null

    fun getTrainings(): Array<Training> {
        if (trainings == null) {
            trainings = loadTrainings()
        }

        return trainings!!
    }

    private fun loadTrainings() : Array<Training> {
        return arrayOf(
                Training(
                        name = "トレーニング名",
                        category = "カテゴリ", // カテゴリ画像URI
                        description = "トレーニングの説明文",
                        image = "null", // カテゴリ画像URI
                        target = "null"
                )
        )
    }
}