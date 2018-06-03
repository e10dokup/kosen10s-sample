package net.kosen10s.example.presenter

import net.kosen10s.example.datasource.MatchingPartnerDataSource
import net.kosen10s.example.datasource.PointDataSource
import net.kosen10s.example.entity.MatchingPartner
import net.kosen10s.example.entity.Point
import net.kosen10s.example.view.navigator.MatchingNavigator
import net.kosen10s.example.view.screen.matching.MatchingActivity

class MatchingActivityPresenter constructor(
        activity: MatchingActivity
) {

    private val activity = activity
    private val navigator = MatchingNavigator(activity)
    private val matchingPartnerDataSource = MatchingPartnerDataSource(activity)
    private val pointDataSource = PointDataSource(activity)

    fun onNavigationMain() {
        navigator.navigateToMain()
    }

    fun onNextPartner(necessaryPoint: Int) {
        pointDataSource.consumePoints(necessaryPoint)
    }

    fun getPartners(onSuccess: ((Array<MatchingPartner>) -> Unit)) {
        val point = getCurrentPoints()
        onSuccess.invoke(matchingPartnerDataSource.getAvailableItems(point))
    }

    fun getCurrentPoints(): Point {
        return pointDataSource.getPoints()
    }

}