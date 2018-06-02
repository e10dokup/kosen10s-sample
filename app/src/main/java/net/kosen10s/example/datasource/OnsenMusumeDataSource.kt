package net.kosen10s.example.datasource

import android.app.Activity
import android.util.Log
import com.squareup.moshi.Moshi
import net.kosen10s.example.ApplicationJsonAdapterFactory
import net.kosen10s.example.entity.OnsenMusume
import net.kosen10s.example.entity.OnsenMusumeData
import net.kosen10s.example.entity.Point
import net.kosen10s.example.ext.random
import java.util.Locale.filter

/**
 * Created by arayaryoma on 6/2/18.
 */

class OnsenMusumeDataSource(activity: Activity) {
    val activity = activity
    val moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()!!

    fun getMusume(point: Point, characterId: String? = null): OnsenMusume {
        var id = characterId
        if (id == null) {
            id = (0..2).random().toString()
        }
        val list = getList()
        val characterData = list.filter { it.id == id }.toTypedArray()[0]
        characterData.speech_texts.sortBy { it.point }
        var speechText = ""
        for ((i, value) in characterData.speech_texts.withIndex()) {
            if (point.point < 1) {
                speechText = value.text
                break
            }
            if (point.point < value.point) {
                if (i == 0) {
                    speechText = value.text
                    break
                }
                speechText = characterData.speech_texts[i - 1].text
                break
            }
            if (i == characterData.speech_texts.lastIndex) {
                speechText = value.text
            }
        }
        return OnsenMusume(characterData.id, characterData.name, characterData.image_name, speechText)
    }

    fun getList(): Array<OnsenMusumeData> {
        val jsonAdapter = moshi.adapter(Array<OnsenMusumeData>::class.java)
        var json = ""
        try {
            val inputStream = activity.assets.open("onsen_musume_data.json")
            json = inputStream.bufferedReader().use { it.readText() }

        } catch (e: Exception) {
            Log.d("Reading file error", e.toString())
        }
        return jsonAdapter.fromJson(json) ?: return arrayOf()
    }
}

