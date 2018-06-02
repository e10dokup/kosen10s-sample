package net.kosen10s.example.datasource

import android.app.Activity
import android.content.Context
import net.kosen10s.example.entity.Point

/**
 * Created by arayaryoma on 6/2/18.
 */

class PointDataSource(activity: Activity) {
    private val prefKey = "point"
    private val mPref = activity.getPreferences(Context.MODE_PRIVATE)!!
    fun getPoints(): Point {
        val point = mPref.getInt(prefKey, 0)
        return Point(point)
    }

    fun addPoints(addedPoints: Int): Point {
        val prePoints = getPoints().point
        val totalPoints = prePoints + addedPoints
        with(mPref.edit()) {
            putInt(prefKey, totalPoints)
            commit()
        }
        return Point(totalPoints)
    }

    fun consumePoints(consumedPoints: Int): Point {
        val prePoints = getPoints().point
        val totalPoints = prePoints - consumedPoints
        with(mPref.edit()) {
            putInt(prefKey, totalPoints)
            commit()
        }
        return Point(totalPoints)
    }

    fun resetPoints(): Point {
        with(mPref.edit()) {
            putInt(prefKey, 0)
            commit()
        }
        return Point(0)
    }
}
