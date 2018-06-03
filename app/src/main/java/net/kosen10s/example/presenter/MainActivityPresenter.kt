package net.kosen10s.example.presenter

import net.kosen10s.example.datasource.OnsenMusumeDataSource
import net.kosen10s.example.datasource.PointDataSource
import net.kosen10s.example.datasource.TrainingDataSource
import net.kosen10s.example.entity.OnsenMusume
import net.kosen10s.example.entity.Point
import net.kosen10s.example.entity.Training
import net.kosen10s.example.view.navigator.MainNavigator
import net.kosen10s.example.view.screen.main.MainActivity

class MainActivityPresenter constructor(
        activity: MainActivity
) {

    companion object {
        const val TRAINING_END_COUNT = 150
    }

    private val activity = activity
    private val navigator = MainNavigator(activity)
    private var moved_count = 0
    private val trainingDataSource = TrainingDataSource(activity)
    private val onsenMusumeDataSource = OnsenMusumeDataSource(activity)
    private val pointDataSource = PointDataSource(activity)

    fun onNavigationMatching() {
        navigator.navigateToMatching()
    }

    fun onNextTraining() {
        pointDataSource.addPoints(moved_count)
        moved_count = 0
    }

    fun countup() {
        if (moved_count == 0) {
            activity.onBeginTraining()
        }
        if (moved_count < TRAINING_END_COUNT) {
            moved_count++
        }
    }

    fun count(): Int {
        return moved_count
    }

    fun getTrainings(onSuccess: ((Array<Training>) -> Unit)) {
        trainingDataSource.getTrainings {
            onSuccess.invoke(it)
        }
    }

    fun getCurrentPoints(): Point {
        return pointDataSource.getPoints()
    }

    fun getOnsenMusume(): OnsenMusume {
        val point = getCurrentPoints()
        return onsenMusumeDataSource.getMusume(point)
    }
}