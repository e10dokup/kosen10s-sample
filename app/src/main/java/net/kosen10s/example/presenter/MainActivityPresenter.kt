package net.kosen10s.example.presenter

import net.kosen10s.example.view.navigator.MainNavigator
import net.kosen10s.example.view.screen.main.MainActivity

class MainActivityPresenter constructor(
        activity: MainActivity
) {

    private val navigator = MainNavigator(activity)

    fun onClickStartButton() {
        navigator.navigateToNews()
    }

}