package net.kosen10s.example.presenter

import net.kosen10s.example.navigater.MainNavigator
import net.kosen10s.example.view.main.MainActivity

class MainActivityPresenter constructor(
        activity: MainActivity
) {

    private val navigator = MainNavigator(activity)

    fun onClickStartButton() {
        navigator.navigateToNews()
    }

}