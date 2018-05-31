package net.kosen10s.example.view.navigator

import android.content.Intent
import net.kosen10s.example.view.screen.articles.ArticlesActivity
import net.kosen10s.example.view.screen.main.MainActivity

class MainNavigator constructor(
        private val activity: MainActivity
) {

    fun navigateToNews() {
        val intent = Intent(activity, ArticlesActivity::class.java)
        activity.startActivity(intent)
    }

}