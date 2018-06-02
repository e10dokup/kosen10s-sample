package net.kosen10s.example.view.item

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Position
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection
import net.kosen10s.example.R
import net.kosen10s.example.entity.Training


@Layout(R.layout.muscle_card)
class MuscleCard constructor(
        private val context: Context,
        private val training: Training,
        private val callback: Callback?
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
    var position: Int = 0

    @Resolve
    fun onResolved() {
        Glide.with(context).load("https://placehold.jp/320x320.png").into(muscleImage)
        Glide.with(context).load("https://placehold.jp/640x640.png").into(trainingImage)
        trainingTitle.text = training.name
        trainingDescription.text = training.description
        trainingTarget.text = "目標：" + training.target
    }

    @SwipeOutDirectional
    fun onSwipeOutDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)

        when(direction) {
            SwipeDirection.TOP
            -> callback?.onSwipeUp(training, this)
            SwipeDirection.LEFT, SwipeDirection.LEFT_BOTTOM, SwipeDirection.LEFT_TOP
            -> callback?.onSwipeOut(training, this)
        }
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState")
    }

    @SwipeInDirectional
    fun onSwipeInDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)

        when(direction) {
            SwipeDirection.BOTTOM
            -> callback?.onSwipeDown(training, this)
            SwipeDirection.RIGHT, SwipeDirection.RIGHT_BOTTOM, SwipeDirection.RIGHT_TOP
            -> callback?.onSwipeIn(training, this)
        }
    }

    @SwipingDirection
    fun onSwipingDirection(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipingDirection " + direction.name)
    }

    interface Callback {
        fun onSwipeUp(training: Training, card: MuscleCard)
        fun onSwipeOut(training: Training, card: MuscleCard)
        fun onSwipeIn(training: Training, card: MuscleCard)
        fun onSwipeDown(training: Training, card: MuscleCard)
    }
}