package net.kosen10s.example.view.screen.main

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.kosen10s.example.R
import net.kosen10s.example.entity.Training
import net.kosen10s.example.ext.dpToPx
import net.kosen10s.example.presenter.MainActivityPresenter
import net.kosen10s.example.view.item.MuscleCard
import net.kosen10s.example.view.item.OnsenMusumeCard

class MainActivity : AppCompatActivity(), SensorEventListener, MuscleCard.Callback {

    private lateinit var sensorManager: SensorManager

    companion object {
        const val MOVE_THRESHOLD = 10 * 10 + 10 * 10
    }

    private val presenter by lazy {
        MainActivityPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom_navigation
        bottom_navigation.selectedItemId = R.id.nav_camera
        bottom_navigation.isEnabled = false

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_camera -> return@setOnNavigationItemSelectedListener false
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

        sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    override fun onResume() {
        super.onResume()
        // Listenerの登録
        val accel: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(this as SensorEventListener, accel, SensorManager.SENSOR_DELAY_NORMAL)

        presenter.getTrainings {
            it.forEach {
                swipe_view.addView(MuscleCard(this, it, this))
            }
        }
        swipe_view.addView(OnsenMusumeCard(this, presenter.getOnsenMusume(), swipe_view))
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this as SensorEventListener)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val sensorX: Float
        val sensorY: Float
        val sensorZ: Float

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            sensorX = event.values[0]
            sensorY = event.values[1]
            sensorZ = event.values[2]

            val v = sensorX * sensorX + sensorY * sensorY + sensorZ * sensorZ

            if (v > MOVE_THRESHOLD) {
                presenter.countup()
                points.text = presenter.count().toString()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSwipeUp(training: Training, card: MuscleCard) {
    }

    override fun onSwipeOut(training: Training, card: MuscleCard) {
        swipe_view.addView(MuscleCard(this, training, this))
    }

    override fun onSwipeIn(training: Training, card: MuscleCard) {
    }

    override fun onSwipeDown(training: Training, card: MuscleCard) {
    }
}
