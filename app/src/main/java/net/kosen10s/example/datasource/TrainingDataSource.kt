package net.kosen10s.example.datasource

import android.app.Activity
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
                        description = "トレーニングの説明文",
                        category = "カテゴリ",
                        image = 0,
                        targetNum = 30
                )
        )
    }
}