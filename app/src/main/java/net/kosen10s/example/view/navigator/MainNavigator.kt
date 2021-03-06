package net.kosen10s.example.view.navigator

import android.content.Intent
import net.kosen10s.example.view.screen.main.MainActivity
import net.kosen10s.example.view.screen.matching.MatchingActivity

class MainNavigator constructor(
        private val activity: MainActivity
) {

    fun navigateToMatching() {
        val intent = Intent(activity, MatchingActivity::class.java)
        activity.startActivity(intent)
    }

}