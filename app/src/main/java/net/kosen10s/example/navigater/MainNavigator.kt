package net.kosen10s.example.navigater

import android.content.Context
import android.content.Intent
import net.kosen10s.example.view.main.MainActivity

class MainNavigator constructor(
        private val activity: MainActivity
) {

    fun navigateToNews() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

}