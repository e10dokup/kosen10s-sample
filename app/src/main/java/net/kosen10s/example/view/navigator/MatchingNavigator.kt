package net.kosen10s.example.view.navigator

import android.content.Intent
import net.kosen10s.example.view.screen.main.MainActivity
import net.kosen10s.example.view.screen.matching.MatchingActivity

class MatchingNavigator constructor(
        private val activity: MatchingActivity
) {

    fun navigateToMain() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

}