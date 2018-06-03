package net.kosen10s.example.presenter

import net.kosen10s.example.datasource.OnsenMusumeDataSource
import net.kosen10s.example.datasource.PointDataSource
import net.kosen10s.example.datasource.TrainingDataSource
import net.kosen10s.example.entity.MatchingPartner
import net.kosen10s.example.entity.Point
import net.kosen10s.example.view.navigator.MatchingNavigator
import net.kosen10s.example.view.screen.matching.MatchingActivity

class MatchingActivityPresenter constructor(
        activity: MatchingActivity
) {

    private val activity = activity
    private val navigator = MatchingNavigator(activity)
    private var moved_count = 0
    private val trainingDataSource = TrainingDataSource(activity)
    private val onsenMusumeDataSource = OnsenMusumeDataSource(activity)
    private val pointDataSource = PointDataSource(activity)

    fun onNavigationMain() {
        navigator.navigateToMain()
    }

    fun onNextPartner(necessaryPoint: Int) {
        pointDataSource.consumePoints(necessaryPoint)
    }

    fun getPartners(onSuccess: ((Array<MatchingPartner>) -> Unit)) {
        val point = getCurrentPoints()
//        trainingDataSource.getTrainings {
//            onSuccess.invoke(it)
//        }
    }

    fun getCurrentPoints(): Point {
        return pointDataSource.getPoints()
    }

}