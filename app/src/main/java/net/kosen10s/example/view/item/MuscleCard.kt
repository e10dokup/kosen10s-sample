package net.kosen10s.example.view.item

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Position
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import net.kosen10s.example.R



@Layout(R.layout.muscle_card)
class MuscleCard constructor(
        private val context: Context,
        private val swipeView: SwipePlaceHolderView
) {

    @View(R.id.muscle_image)
    lateinit var muscleImage: ImageView

    @View(R.id.training_title)
    lateinit var trainingTitle: TextView

    @View(R.id.training_image)
    lateinit var trainingImage: ImageView

    @View(R.id.training_description)
    lateinit var trainingDescription: TextView

    @View(R.id.training_target)
    lateinit var trainingTarget: TextView

    @JvmField
    @Position
    var position: Int = 0;

    @Resolve
    fun onResolved() {
        Glide.with(context).load("https://placehold.jp/320x320.png").into(muscleImage)
        Glide.with(context).load("https://placehold.jp/640x640.png").into(trainingImage)
        trainingTitle.text = "ここにタイトル"
        trainingDescription.text = "ここに説明"
        trainingTarget.text = "目標：いっぱい"
    }

    @SwipeOut
    fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        swipeView.addView(this)
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }
}