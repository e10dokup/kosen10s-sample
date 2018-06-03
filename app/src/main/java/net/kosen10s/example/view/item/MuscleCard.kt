package net.kosen10s.example.view.item

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Position
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection
import net.kosen10s.example.R
import net.kosen10s.example.entity.Training
import java.util.*


@Layout(R.layout.muscle_card)
class MuscleCard constructor(
        private val context: Context,
        private val training: Training,
        private val callback: Callback?
) {
    @com.mindorks.placeholderview.annotations.View(R.id.muscle_image)
    lateinit var muscleImage: ImageView

    @com.mindorks.placeholderview.annotations.View(R.id.training_title)
    lateinit var trainingTitle: TextView

    @com.mindorks.placeholderview.annotations.View(R.id.training_image)
    lateinit var trainingImage: ImageView

    @com.mindorks.placeholderview.annotations.View(R.id.training_description)
    lateinit var trainingDescription: TextView

    @com.mindorks.placeholderview.annotations.View(R.id.training_target)
    lateinit var trainingTarget: TextView

    @com.mindorks.placeholderview.annotations.View(R.id.training_timer)
    lateinit var trainingTimer: TextView

    @JvmField
    @Position
    var position: Int = 0

    private var elapsed = 0
    private var timer : Timer? = null

    @Resolve
    fun onResolved() {
        val categoryId = context.resources.getIdentifier(training.category, "drawable", context.packageName)
        val imageId = context.resources.getIdentifier(training.image, "drawable", context.packageName)
        Glide.with(context).load(categoryId).into(muscleImage)
        Glide.with(context).load(imageId).into(trainingImage)
        trainingTitle.text = training.name
        trainingDescription.text = training.description
        trainingTarget.text = "目標：" + training.target
    }

    @SwipeOutDirectional
    fun onSwipeOutDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)
        timer?.cancel()
        timer = null

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
        timer?.cancel()
        timer = null

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

    fun onStartTimer() {
        elapsed = 0
        updateTrainingTimer()
        trainingTimer.visibility = View.VISIBLE
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                elapsed++
                updateTrainingTimer()
            }
        }, 0, 10)
    }

    private fun updateTrainingTimer() {
        val min = elapsed * 10 / 1000 / 60
        val sec = elapsed * 10 / 1000 % 60
        val msec = (elapsed * 10 - sec * 1000 - min * 1000 * 60) / 10
        trainingTimer.text = "%01d:%02d.%02d".format(min, sec, msec)
    }

    interface Callback {
        fun onSwipeUp(training: Training, card: MuscleCard)
        fun onSwipeOut(training: Training, card: MuscleCard)
        fun onSwipeIn(training: Training, card: MuscleCard)
        fun onSwipeDown(training: Training, card: MuscleCard)
    }
}