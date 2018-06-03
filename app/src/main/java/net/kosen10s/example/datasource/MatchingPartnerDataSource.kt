package net.kosen10s.example.datasource

import android.app.Activity
import android.util.Log
import com.squareup.moshi.Moshi
import net.kosen10s.example.ApplicationJsonAdapterFactory
import net.kosen10s.example.entity.MatchingPartner
import net.kosen10s.example.entity.MatchingPartnerData
import net.kosen10s.example.entity.Point

class MatchingPartnerDataSource(activity: Activity) {
    val activity = activity
    val moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()!!

    fun getAvailableItems(point: Point): Array<MatchingPartner> {
        val partnerListData = getData()
        val res: MutableList<MatchingPartner> = mutableListOf()
        for (partner in partnerListData) {
            if (point.point >= partner.necessary_points) {
                res.add(MatchingPartner(partner.id, partner.name, partner.image_name, partner.necessary_points, partner.favorite_muscle_image_name))
            }
        }
        return res.toTypedArray()
    }

    private fun getData(): Array<MatchingPartnerData> {
        val jsonAdapter = moshi.adapter(Array<MatchingPartnerData>::class.java)
        var json = ""
        try {
            val inputStream = activity.assets.open("matching_partners_data.json")
            json = inputStream.bufferedReader().use { it.readText() }

        } catch (e: Exception) {
            Log.d("Reading file error", e.toString())
        }
        return jsonAdapter.fromJson(json) ?: return arrayOf()
    }
}
