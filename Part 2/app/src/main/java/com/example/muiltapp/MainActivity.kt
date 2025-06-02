package com.example.muiltapp

import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var rainSound: MediaPlayer? = null
    private var oceanSound: MediaPlayer? = null
    private var forestSound: MediaPlayer? = null
    private var cafeSound: MediaPlayer? = null

    private var currentlyPlaying: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeMediaPlayers()

        findViewById<ImageButton>(R.id.rainButton).setOnClickListener {
            playSound(rainSound, "rain")
        }
        findViewById<ImageButton>(R.id.oceanButton).setOnClickListener {
            playSound(oceanSound, "ocean")
        }
        findViewById<ImageButton>(R.id.forestButton).setOnClickListener {
            playSound(forestSound, "forest")
        }
        findViewById<ImageButton>(R.id.cafeButton).setOnClickListener {
            playSound(cafeSound, "cafe")
        }

        findViewById<Button>(R.id.stopAllBtn).setOnClickListener {
            stopAllSounds()
        }

        findViewById<Button>(R.id.openJournalBtn).setOnClickListener {
            openJournal(it)
        }

        findViewById<Button>(R.id.openSettingsBtn).setOnClickListener {
            openSettings(it)
        }

        findViewById<Button>(R.id.openInstructionsBtn).setOnClickListener {
            openInstructions(it)
        }
    }

    private fun initializeMediaPlayers() {
        rainSound = MediaPlayer.create(this, R.raw.rain_piano)
        oceanSound = MediaPlayer.create(this, R.raw.ocean_piano)
        forestSound = MediaPlayer.create(this, R.raw.forest_piano)
        cafeSound = MediaPlayer.create(this, R.raw.cafe_piano)
    }

    private fun playSound(player: MediaPlayer?, type: String) {
        if (player == null) return

        // If another sound is playing and not paused, show warning
        if (currentlyPlaying != null && currentlyPlaying != type) {
            showConflictWarning()
            return
        }

        // If same sound is paused, resume it
        if (currentlyPlaying == type) {
            player.start()
            return
        }

        // Stop others except this one
        stopAllExcept(type)

        player.isLooping = true
        player.seekTo(0)
        player.start()
        currentlyPlaying = type
    }

    private fun pauseCurrentSound() {
        val currentPlayer = when (currentlyPlaying) {
            "rain" -> rainSound
            "ocean" -> oceanSound
            "forest" -> forestSound
            "cafe" -> cafeSound
            else -> null
        }

        currentPlayer?.pause()
    }

    private fun stopAllSounds() {
        listOf(rainSound, oceanSound, forestSound, cafeSound).forEach {
            it?.stop()
            it?.release()
        }

        currentlyPlaying = null

        initializeMediaPlayers()
    }

    private fun stopAllExcept(exceptType: String) {
        if (exceptType != "rain") {
            rainSound?.pause()
            rainSound?.seekTo(0)
        }
        if (exceptType != "ocean") {
            oceanSound?.pause()
            oceanSound?.seekTo(0)
        }
        if (exceptType != "forest") {
            forestSound?.pause()
            forestSound?.seekTo(0)
        }
        if (exceptType != "cafe") {
            cafeSound?.pause()
            cafeSound?.seekTo(0)
        }
    }

    private fun showConflictWarning() {
        AlertDialog.Builder(this)
            .setTitle("Sound Conflict")
            .setMessage("Please pause the current sound before playing another.")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAllSounds()
    }

    fun openJournal(view: View) {
        startActivity(Intent(this, JournalActivity::class.java))
    }

    fun openSettings(view: View) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    fun openInstructions(view: View) {
        val instructionsModal = InstructionsModal()
        instructionsModal.show(supportFragmentManager, "InstructionsModal")
    }
}
