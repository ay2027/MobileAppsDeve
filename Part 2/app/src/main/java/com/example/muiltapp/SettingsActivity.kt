package com.example.muiltapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        preferences = getSharedPreferences("MindHuePrefs", MODE_PRIVATE)

        val themeSwitch = findViewById<Switch>(R.id.switchTheme)
        val fontSizeGroup = findViewById<RadioGroup>(R.id.fontSizeGroup)

        // Load saved preferences
        themeSwitch.isChecked = preferences.getBoolean("darkMode", false)
        val savedFontSize = preferences.getString("fontSize", "medium")
        when (savedFontSize) {
            "small" -> fontSizeGroup.check(R.id.fontSmall)
            "medium" -> fontSizeGroup.check(R.id.fontMedium)
            "large" -> fontSizeGroup.check(R.id.fontLarge)
        }

        // Save on toggle
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("darkMode", isChecked).apply()
            Toast.makeText(this, "Theme saved", Toast.LENGTH_SHORT).show()
        }

        fontSizeGroup.setOnCheckedChangeListener { _, checkedId ->
            val size = when (checkedId) {
                R.id.fontSmall -> "small"
                R.id.fontMedium -> "medium"
                R.id.fontLarge -> "large"
                else -> "medium"
            }
            preferences.edit().putString("fontSize", size).apply()
            Toast.makeText(this, "Font size saved", Toast.LENGTH_SHORT).show()
        }
    }
}
