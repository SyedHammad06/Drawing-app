package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null
    private var currentColor: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setBrushSize(20.toFloat())

        val linearLayoutColors = findViewById<LinearLayout>(R.id.colors_btn)
        currentColor = linearLayoutColors[1] as ImageButton
        currentColor!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.palatte_pressed))

        val brushBtn = findViewById<ImageButton>(R.id.brush_btn)
        brushBtn.setOnClickListener {
            showBrushSizeDialog()
        }
    }

    private fun showBrushSizeDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush)
        brushDialog.setTitle("Brush size: ")
        val smallBtn = brushDialog.findViewById<ImageView>(R.id.small_brush)
        smallBtn.setOnClickListener{
            drawingView?.setBrushSize(10.toFloat())
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.findViewById<ImageView>(R.id.medium_brush)
        mediumBtn.setOnClickListener {
            drawingView?.setBrushSize(20.toFloat())
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.findViewById<ImageView>(R.id.large_brush)
        largeBtn.setOnClickListener {
            drawingView?.setBrushSize(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if (currentColor !== view) {
            val imageBtn = view as ImageButton
            val colorTag = imageBtn.tag.toString()
            drawingView?.setColor(colorTag)
            imageBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.palatte_pressed))
            currentColor!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.palatte))
            currentColor = view
        }
    }
}