package net.kosen10s.example.view.item

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Position
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection
import net.kosen10s.example.R
import net.kosen10s.example.entity.OnsenMusume


@Layout(R.layout.onsen_musume_card)
class OnsenMusumeCard constructor(
        private val context: Context,
        private val onsenMusume: OnsenMusume,
        private val swipeView: SwipePlaceHolderView,
        private val callback: Callback?
) {

    @View(R.id.onsen_musume_image)
    lateinit var onsenMusumeImage: ImageView

    @View(R.id.onsen_musume_name)
    lateinit var onsenMusumeName: TextView

    @View(R.id.onsen_musume_speech)
    lateinit var onsenMusumeSpeech: TextView

    @JvmField
    @Position
    var position: Int = 0

    @Resolve
    fun onResolved() {
        val imageId = context.resources.getIdentifier(onsenMusume.imageName, "drawable", context.packageName)
        Glide.with(context).load(imageId).into(onsenMusumeImage)
        onsenMusumeName.text = "${onsenMusume.name} ちゃん"
        onsenMusumeSpeech.text = onsenMusume.speechText
    }

    @SwipeOutDirectional
    fun onSwipeOutDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)
        if (direction.direction == SwipeDirection.TOP.direction) {
            callback?.onSwipeUp(onsenMusume)
        }
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState")
        swipeView.alpha = 1f
    }

    @SwipeInDirectional
    fun onSwipeInDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeInDirectional " + direction.name)
    }

    @SwipingDirection
    fun onSwipingDirection(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipingDirection " + direction.name)
    }

    interface Callback {
        fun onSwipeUp(onsenMusume: OnsenMusume)
    }
}
