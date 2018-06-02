package net.kosen10s.example.presenter

import net.kosen10s.example.datasource.TrainingDataSource
import net.kosen10s.example.entity.Training
import net.kosen10s.example.view.navigator.MainNavigator
import net.kosen10s.example.view.screen.main.MainActivity

class MainActivityPresenter constructor(
        activity: MainActivity
) {

    private val navigator = MainNavigator(activity)
    private var moved_count = 0
    private val trainingDataSource = TrainingDataSource(activity)

    fun onClickStartButton() {
        navigator.navigateToNews()
    }

    fun countup() {
        moved_count++
    }

    fun count(): Int {
        return moved_count
    }

    fun getTrainings(onSuccess: ((Array<Training>) -> Unit)) {
        trainingDataSource.getTrainings {
            onSuccess.invoke(it)
        }
    }

}