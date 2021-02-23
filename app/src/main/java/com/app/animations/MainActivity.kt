package com.app.animations

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var view: View
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomSheet = BottomSheetDialog(this)
        view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheet.setContentView(view)

        clickHere.setOnClickListener {
            setVisibilityToItems(false)
            bottomSheet.show()
            animateWorkoutIcon()
            handler = Handler()
            handler.postDelayed(
                {
                    animateContentInBottomSheet()
                }, 800
            )
        }
    }

    // Sets visibility for view objects to visibile and invisible as required.
    private fun setVisibilityToItems(isVisible: Boolean) {
        if (isVisible) {
            view.goPremium.visibility = View.VISIBLE
            view.forUnlimitedHistory.visibility = View.VISIBLE
            view.line.visibility = View.VISIBLE
            view.bulletPointSet1.visibility = View.VISIBLE
            view.bulletPointSet2.visibility = View.VISIBLE
            view.bulletPointSet3.visibility = View.VISIBLE
            view.buttonTryWeeks.visibility = View.VISIBLE
        } else {
            view.goPremium.visibility = View.INVISIBLE
            view.forUnlimitedHistory.visibility = View.INVISIBLE
            view.line.visibility = View.INVISIBLE
            view.bulletPointSet1.visibility = View.INVISIBLE
            view.bulletPointSet2.visibility = View.INVISIBLE
            view.bulletPointSet3.visibility = View.INVISIBLE
            view.buttonTryWeeks.visibility = View.INVISIBLE
        }
    }

    // Animates the workout icon.
    private fun animateWorkoutIcon() {
        val zoomIn = AnimationUtils.loadAnimation(view.context, R.anim.zoom_in)
        val zoomOut = AnimationUtils.loadAnimation(view.context, R.anim.zoom_out)

        view.workoutIcon.startAnimation(zoomIn)
        handler = Handler()
        handler.postDelayed(
            {
                view.workoutIcon.startAnimation(zoomOut)
            }, 500
        )
    }

    // applies animations to the view objects.
    private fun animateContentInBottomSheet() {

        val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
        val fadeInFromRight = AnimationUtils.loadAnimation(view.context, R.anim.fade_in_from_right)
        val fadeInFromLeft = AnimationUtils.loadAnimation(view.context, R.anim.fade_in_from_left)
        setVisibilityToItems(true)
        view.goPremium.startAnimation(fadeInFromRight)
        view.forUnlimitedHistory.startAnimation(fadeInFromLeft)
        view.line.startAnimation(fadeIn)
        view.bulletPointSet1.startAnimation(fadeIn)
        view.bulletPointSet2.startAnimation(fadeIn)
        view.bulletPointSet3.startAnimation(fadeIn)
        view.buttonTryWeeks.startAnimation(fadeIn)
    }
}