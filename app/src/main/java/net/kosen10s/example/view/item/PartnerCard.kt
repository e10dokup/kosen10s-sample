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
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection
import net.kosen10s.example.R
import net.kosen10s.example.entity.MatchingPartner


@Layout(R.layout.matching_card)
class PartnerCard constructor(
        private val context: Context,
        private val partner: MatchingPartner,
        private val callback: Callback?
) {
    @com.mindorks.placeholderview.annotations.View(R.id.muscle_image)
    lateinit var muscleImage: ImageView

    @com.mindorks.placeholderview.annotations.View(R.id.name)
    lateinit var name: TextView

    @com.mindorks.placeholderview.annotations.View(R.id.matching_image)
    lateinit var matchingImage: ImageView

    @com.mindorks.placeholderview.annotations.View(R.id.necessary_point)
    lateinit var necessaryPoint: TextView

    @JvmField
    @Position
    var position: Int = 0

    @Resolve
    fun onResolved() {
        val categoryId = context.resources.getIdentifier(partner.favoriteMuscleImageName, "drawable", context.packageName)
        val imageId = context.resources.getIdentifier(partner.imageName, "drawable", context.packageName)
        Glide.with(context).load(categoryId).into(muscleImage)
        Glide.with(context).load(imageId).into(matchingImage)
        name.text = partner.name
        necessaryPoint.text = partner.necessaryPoints.toString() + "pts"
    }

    @SwipeOutDirectional
    fun onSwipeOutDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)

        when(direction) {
            SwipeDirection.TOP
            -> callback?.onSwipeUp(partner, this)
            SwipeDirection.LEFT, SwipeDirection.LEFT_BOTTOM, SwipeDirection.LEFT_TOP
            -> callback?.onSwipeOut(partner, this)
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
            -> callback?.onSwipeDown(partner, this)
            SwipeDirection.RIGHT, SwipeDirection.RIGHT_BOTTOM, SwipeDirection.RIGHT_TOP
            -> callback?.onSwipeIn(partner, this)
        }
    }

    @SwipingDirection
    fun onSwipingDirection(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipingDirection " + direction.name)
    }

    interface Callback {
        fun onSwipeUp(partner: MatchingPartner, card: PartnerCard)
        fun onSwipeOut(partner: MatchingPartner, card: PartnerCard)
        fun onSwipeIn(partner: MatchingPartner, card: PartnerCard)
        fun onSwipeDown(partner: MatchingPartner, card: PartnerCard)
    }
}