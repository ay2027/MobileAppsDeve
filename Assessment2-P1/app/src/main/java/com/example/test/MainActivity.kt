package com.example.test

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedColors: TextView
    private lateinit var tvColorName: TextView
    private lateinit var viewColorOutput: View

    private val selectedColors = mutableListOf<String>()

    private val colorMap = mapOf(
        "Red" to Color.RED,
        "Blue" to Color.BLUE,
        "Green" to Color.GREEN,
        "Cyan" to "#00FFFF".toColorInt(),
        "Magenta" to "#FF00FF".toColorInt(),
        "Yellow" to "#FFFF00".toColorInt(),
        "White" to Color.WHITE,
        "Orange" to "#FFA500".toColorInt(),
        "Green Shade" to "#66BB66".toColorInt(),
        "Electric Lime" to "#CCFF00".toColorInt(),
        "Rose" to "#FF3399".toColorInt(),
        "Deep Purple" to "#4B0082".toColorInt(),
        "Teal" to "#008080".toColorInt(),
        "Shade Pink" to "#FFC0CB".toColorInt(),
        "Light Blue" to "#ADD8E6".toColorInt(),
        "Mint" to "#98FF98".toColorInt()
    )

    private val mixRules = mapOf(
        setOf("Red", "Green") to "Yellow",
        setOf("Red", "Blue") to "Magenta",
        setOf("Green", "Blue") to "Cyan",
        setOf("Red", "Green", "Blue") to "White",

        setOf("Yellow","Red") to "Orange",
        setOf("Yellow","Blue") to "Green Shade",
        setOf("Yellow","Green") to "Electric Lime",

        setOf("Magenta","Red") to "Rose",
        setOf("Magenta","Blue") to "Deep Purple",
        setOf("Magenta","Green") to "White",

        setOf("Cyan","Red") to "White",
        setOf("Cyan","Blue") to "Blue",
        setOf("Cyan","Green") to "Teal",

        setOf("White", "Red") to "Shade Pink",
        setOf("White", "Blue") to "Light Blue",
        setOf("White", "Green") to "Mint"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedColors = findViewById(R.id.tvSelectedColors)
        tvColorName = findViewById(R.id.tvColorName)
        viewColorOutput = findViewById(R.id.viewColorOutput)

        val btnRed = findViewById<MaterialButton>(R.id.btnRed)
        val btnBlue = findViewById<MaterialButton>(R.id.btnBlue)
        val btnGreen = findViewById<MaterialButton>(R.id.btnGreen)
        val btnEquals = findViewById<MaterialButton>(R.id.btnEquals)
        val btnClear = findViewById<MaterialButton>(R.id.btnClear)

        btnRed.setOnClickListener { addColor("Red") }
        btnBlue.setOnClickListener { addColor("Blue") }
        btnGreen.setOnClickListener { addColor("Green") }
        btnEquals.setOnClickListener { mixColors() }
        btnClear.setOnClickListener { clearColors() }

        updateSelectedColorsDisplay()
    }

    private fun addColor(color: String) {
        if (selectedColors.size < 2) {
            selectedColors.add(color)
            updateSelectedColorsDisplay()
            tvColorName.text = ""
            viewColorOutput.setBackgroundColor(Color.WHITE)
        }else {
                tvColorName.text = "Max 2 colors allowed!"
        }
    }

    private fun mixColors() {
        if (selectedColors.size < 2) {
            tvColorName.text = "Oops! Please pick exactly 2 colors! \uD83C\uDFA8"
            return
        }

        val mixResult = mixRules[selectedColors.toSet()]
        if (mixResult != null) {
            selectedColors.clear()
            selectedColors.add(mixResult)
            updateSelectedColorsDisplay()
            tvColorName.text = "You made: $mixResult! 🎉"
            viewColorOutput.setBackgroundColor(colorMap[mixResult] ?: Color.GRAY)
        } else {
            tvColorName.text = "Hmm... Try a different combo!"
            viewColorOutput.setBackgroundColor(Color.GRAY)
        }
    }

    private fun clearColors() {
        selectedColors.clear()
        updateSelectedColorsDisplay()
        tvColorName.text = ""
        viewColorOutput.setBackgroundColor(Color.WHITE)
    }

    private fun updateSelectedColorsDisplay() {
        tvSelectedColors.text = if (selectedColors.isEmpty())
            "🎨 Pick your colors! 🌈"
        else
            selectedColors.joinToString(" + ") + "  = 🤩 Mix time!"
    }
}
