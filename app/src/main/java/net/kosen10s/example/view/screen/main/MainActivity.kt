package net.kosen10s.example.view.screen.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.kosen10s.example.R
import net.kosen10s.example.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity() {

    private val presenter by lazy {
        MainActivityPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener {
            presenter.onClickStartButton()
        }

        // bottom_navigation
        bottom_navigation.selectedItemId = R.id.nav_camera
        bottom_navigation.isEnabled = false

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_camera -> return@setOnNavigationItemSelectedListener false
                R.id.nav_gallery -> return@setOnNavigationItemSelectedListener false
                R.id.nav_slideshow -> return@setOnNavigationItemSelectedListener false
                R.id.nav_manage -> return@setOnNavigationItemSelectedListener false
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}
