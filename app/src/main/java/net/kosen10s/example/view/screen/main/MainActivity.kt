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
    }
}
