package net.kosen10s.example.view.screen.matching

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.kosen10s.example.R
import net.kosen10s.example.entity.MatchingPartner
import net.kosen10s.example.ext.dpToPx
import net.kosen10s.example.presenter.MatchingActivityPresenter
import net.kosen10s.example.view.item.PartnerCard

class MatchingActivity : AppCompatActivity(), PartnerCard.Callback {

    companion object {
        const val TRAINING_RIGHT_BUTTON_TEXT = "LIKE"
        const val TRAINING_LEFT_BUTTON_TEXT = "NOPE"

    }

    private val presenter by lazy {
        MatchingActivityPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching)

        // bottom_navigation
        bottom_navigation.selectedItemId = R.id.nav_slideshow
        bottom_navigation.isEnabled = false

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_camera -> {
                    presenter.onNavigationMain()
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.nav_gallery -> return@setOnNavigationItemSelectedListener false
                R.id.nav_slideshow -> return@setOnNavigationItemSelectedListener false
                R.id.nav_manage -> return@setOnNavigationItemSelectedListener false
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        swipe_view.builder
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(50.dpToPx())
                .setSwipeHorizontalThreshold(50.dpToPx())
                .setHeightSwipeDistFactor(10f)
                .setWidthSwipeDistFactor(5f)

        updatePointText()
    }


    override fun onResume() {
        super.onResume()
        // Listenerの登録
        right_button.text = TRAINING_RIGHT_BUTTON_TEXT
        left_button.text = TRAINING_LEFT_BUTTON_TEXT
        addNewPartnerCards()
        swipe_view.addItemRemoveListener {
            when (it) {
                1 -> {
                    addNewPartnerCards()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSwipeUp(partner: MatchingPartner, card: PartnerCard) {
        presenter.onNextPartner(partner.necessaryPoints)
        updatePointText()
    }

    override fun onSwipeOut(partner: MatchingPartner, card: PartnerCard) {
    }

    override fun onSwipeIn(partner: MatchingPartner, card: PartnerCard) {
        presenter.onNextPartner(partner.necessaryPoints)
        updatePointText()
    }

    override fun onSwipeDown(partner: MatchingPartner, card: PartnerCard) {
    }

    private fun updatePointText() {
        current_points.text = presenter.getCurrentPoints().point.toString() + "pts"
        Log.d("updatePointText", presenter.getCurrentPoints().point.toString())
    }

    private fun addNewPartnerCards() {
        presenter.getPartners {
            it.forEach {
                swipe_view.addView(PartnerCard(this, it, this))
            }
        }
    }
}
