package net.kosen10s.example.datasource

import android.app.Activity
import android.util.Log
import com.squareup.moshi.Moshi
import net.kosen10s.example.entity.Training
import net.kosen10s.example.ApplicationJsonAdapterFactory

class TrainingDataSource(activity: Activity) {
    private var trainings : Array<Training>? = null
    val moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()!!
    val activity = activity

    fun getTrainings(): Array<Training> {
        if (trainings == null) {
            trainings = loadTrainings()
        }
        return trainings!!
    }

    private fun loadTrainings() : Array<Training> {
        val jsonAdapter = moshi.adapter(Array<Training>::class.java)
        var json = ""
        try {
            val inputStream = activity.assets.open("trainings.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (e: Exception) {
            Log.d("Reading file error", e.toString())
        }
        return jsonAdapter.fromJson(json) ?: return arrayOf()
    }
}