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
import net.kosen10s.example.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), SensorEventListener {

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

        start_button.setOnClickListener {
            presenter.onClickStartButton()
        }

        sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    override fun onResume() {
        super.onResume()
        // Listenerの登録
        val accel : Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(this as SensorEventListener, accel, SensorManager.SENSOR_DELAY_NORMAL)
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

            textView.text = "$sensorX\n$sensorY\n$sensorZ"

            val v = sensorX * sensorX + sensorY * sensorY + sensorZ * sensorZ

            if (v > MOVE_THRESHOLD) {
                presenter.countup()
                points.text = presenter.count().toString()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
